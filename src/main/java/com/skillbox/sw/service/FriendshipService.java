package com.skillbox.sw.service;

import com.skillbox.sw.api.request.RequestDialogUsersApi;
import com.skillbox.sw.api.response.AbstractResponse;
import com.skillbox.sw.api.response.FriendshipApi;
import com.skillbox.sw.api.response.FriendshipStatusApi;
import com.skillbox.sw.api.response.ResponsePersonApi;
import com.skillbox.sw.config.jwt.JwtProvider;
import com.skillbox.sw.domain.Friendship;
import com.skillbox.sw.domain.FriendshipStatus;
import com.skillbox.sw.domain.Person;
import com.skillbox.sw.domain.enums.FriendshipCode;
import com.skillbox.sw.mapper.PersonMapper;
import com.skillbox.sw.repository.FriendshipRepository;
import com.skillbox.sw.repository.FriendshipStatusRepository;
import com.skillbox.sw.repository.PersonRepository;
import com.skillbox.sw.util.GenericSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;


@Service
@AllArgsConstructor
public class FriendshipService {

  private FriendshipRepository repository;
  private FriendshipStatusRepository friendshipStatusRepository;
  private PersonRepository personRepository;
  private PersonMapper personMapper;
  private GenericSpecification<Friendship> spec;
  private GenericSpecification<Person> personSpec;

  public Page<ResponsePersonApi> findFriends(
      String token,
      String name,
      Pageable page) {
    Specification<Friendship> s = where(
        spec.byFieldParam("srcPerson", "%", "email", JwtProvider.getUsername(token))
    ).and(
        spec.byFieldParam("dstPerson", "%", "firstName", name)
            .or(spec.byFieldParam("dstPerson", "%", "lastName", name))
    ).and(
        spec.byFieldParam("friendshipStatus", "%", "code", FriendshipCode.FRIEND)
    );
    return repository.findAll(s, page)
        .map(f -> personMapper.personToPersonApi(f.getDstPerson()));
  }

  public List<Friendship> deleteFriend(String token, int id) {
    String myEmail = JwtProvider.getUsername(token);
    personRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    List<Friendship> relations = findRelations(myEmail, id);

    for (Friendship f : relations) {
      if (f.getDstPerson().getId() == id) {
        f.getFriendshipStatus().setCode(FriendshipCode.DECLINED);
      } else if (f.getSrcPerson().getId() == id) {
        f.getFriendshipStatus().setCode(FriendshipCode.SUBSCRIBED);
      }
      repository.save(f);
    }
    return relations;
  }

  public List<Friendship> sendOrApproveRequest(String token, int id) {
    String myEmail = JwtProvider.getUsername(token);
    Person me = personRepository.findByEmail(myEmail)
        .orElseThrow(EntityNotFoundException::new);
    Person givenUser = personRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("There is no such user with id " + id));

    List<Friendship> relations = findRelations(myEmail, id);

    // send request logic
    if (relations.isEmpty()) {
      // TODO : add FRIEND_REQUEST notification
      // friendship status struct: id, 'rfc date','src.email->dst.email', code
      // I subscribing to given user
      FriendshipStatus meToGivenUserStatus = FriendshipStatus.builder()
          .name(myEmail + "->" + givenUser.getEmail())
          .time(LocalDate.now())
          .code(FriendshipCode.SUBSCRIBED).build();
      Friendship meToGivenUserRelation = Friendship.builder()
          .friendshipStatus(meToGivenUserStatus).srcPerson(me).dstPerson(givenUser).build();

      // There's some embarrassing stuff, but this means the following
      // given user RECEIVED request from me
      FriendshipStatus givenUserToMeStatus = FriendshipStatus.builder()
          .name(givenUser.getEmail() + "->" + myEmail).time(LocalDate.now()).code(FriendshipCode.REQUEST).build();
      Friendship givenUserToMeRelation = Friendship.builder()
          .friendshipStatus(givenUserToMeStatus).srcPerson(givenUser).dstPerson(me).build();

      friendshipStatusRepository.save(meToGivenUserStatus);
      friendshipStatusRepository.save(givenUserToMeStatus);
      repository.save(meToGivenUserRelation);
      repository.save(givenUserToMeRelation);

      meToGivenUserStatus = givenUserToMeStatus = null;
      meToGivenUserRelation = meToGivenUserRelation = null;
    }
    // approving request logic assuming that we have bidirectional relation
    else if (relations.size() == 2) {
      for (Friendship f : relations) {
        f.getFriendshipStatus().setCode(FriendshipCode.FRIEND);
        f.getFriendshipStatus().setTime(LocalDate.now());
      }
      repository.saveAll(relations);
    }
    return relations;
  }

  public Page<ResponsePersonApi> getFriendshipRequests(
      String token,
      String name,
      Pageable page) {
    Specification<Friendship> s = where(
        spec.byFieldParam("srcPerson", "%", "email", JwtProvider.getUsername(token))
    ).and(
        spec.byFieldParam("dstPerson", "%", "firstName", name)
            .or(spec.byFieldParam("dstPerson", "%", "lastName", name))
    ).and(
        spec.byFieldParam("friendshipStatus", "%", "code", FriendshipCode.REQUEST)
    );
    return repository.findAll(s, page)
        .map(f -> personMapper.personToPersonApi(f.getDstPerson()));
  }

  public Page<ResponsePersonApi> getRecommendations(Pageable page) {
    // mock request
    Specification<Person> s = personSpec.byField("firstName", "%", "nn")
        .or(personSpec.byField("lastName", "%", "nn"));
    return personRepository.findAll(s, page)
        .map(p -> personMapper.personToPersonApi(p));
  }

  public List<FriendshipStatusApi> checkIfFriends(String token, RequestDialogUsersApi userIdList) {
    String myEmail = JwtProvider.getUsername(token);
    List<Integer> ids = userIdList.getUserIds();
    Specification<Friendship> s =
        spec.byFieldParam("srcPerson", "in", "id", ids)
        .and(spec.byFieldParam("dstPerson", "%", "email", myEmail));
    return repository.findAll(s)
        .stream().map(f ->
            new FriendshipStatusApi(f.getSrcPerson().getId(),
                f.getFriendshipStatus().getCode()
            )
        ).collect(Collectors.toList());
  }

  private List<Friendship> findRelations(String email, int id) {
    Specification<Friendship> s =
        (
            spec.byFieldParam("srcPerson", "%", "email", email)
                .and(spec.byFieldParam("dstPerson", "%", "id", id))
        ).or(
            spec.byFieldParam("dstPerson", "%", "email", email)
                .and(spec.byFieldParam("srcPerson", "%", "id", id))
        );
    return repository.findAll(s);
  }
}
