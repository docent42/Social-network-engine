package com.skillbox.sw.service;

import com.skillbox.sw.api.request.RequestPersonApi;
import com.skillbox.sw.api.request.RequestPostApi;
import com.skillbox.sw.api.response.AbstractResponse;
import com.skillbox.sw.api.response.ReportApi;
import com.skillbox.sw.api.response.ResponsePersonApi;
import com.skillbox.sw.api.response.ResponsePostApi;
import com.skillbox.sw.config.jwt.JwtProvider;
import com.skillbox.sw.domain.Person;
import com.skillbox.sw.domain.Post;
import com.skillbox.sw.mapper.CommentMapper;
import com.skillbox.sw.mapper.DateMapper;
import com.skillbox.sw.mapper.PersonMapper;
import com.skillbox.sw.mapper.PostMapper;
import com.skillbox.sw.repository.PersonRepository;
import com.skillbox.sw.repository.PostCommentRepository;
import com.skillbox.sw.repository.PostLikeRepository;
import com.skillbox.sw.repository.PostRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Transactional(readOnly = false)
@AllArgsConstructor
public class PersonService implements UserDetailsService {

  private PersonRepository personRepository;

  private PostRepository postRepository;

  private PostCommentRepository postCommentRepository;

  private PostLikeRepository postLikeRepository;

  private PersonMapper personMapper;

  private PostMapper postMapper;

  private DateMapper dateMapper;

  private CommentMapper commentMapper;

  @Transactional(readOnly = true)
  public AbstractResponse getCurrentUser(String token) {
    Person person = getCurrentPersonByToken(token);
    return personMapper.personToPersonApi(person);
  }

  public AbstractResponse editCurrentUser(String token, RequestPersonApi requestPersonApi) {
    String email = JwtProvider.getUsername(token);
    Person personBefore = getCurrentPersonByToken(token);
    Person personAfter = personMapper.personApiToPerson(requestPersonApi);
    personAfter.setId(personBefore.getId());
    personAfter.setEmail(email);
    personAfter.setPassword(personBefore.getPassword());
    personRepository.save(personAfter);
    return personMapper.personToPersonApi(personAfter);
  }

  public AbstractResponse deleteCurrentUser(String token) {
    Person person = getCurrentPersonByToken(token);
    person.setDeleted(true);
    personRepository.save(person);
    return new ReportApi("Ok");
  }

  @Transactional(readOnly = true)
  public AbstractResponse getUser(int id) {
    Person person = getPersonById(id);
    return personMapper.personToPersonApi(person);
  }

  @Transactional(readOnly = true)
  public List<ResponsePostApi> getPosts(int id, int offset, int itemPerPage) {
    List<Post> allPosts = postRepository.findAllByAuthorId(id);
    if (allPosts.isEmpty()) {
      throw new EntityNotFoundException("No records were found for this search.");
    }
    List<ResponsePostApi> responsePostApiList = new ArrayList<>();
    Pageable pageable = PageRequest.of(offset / itemPerPage, itemPerPage);
    responsePostApiList.addAll(postMapper.postToResponsePostApi(postRepository.findAllByAuthorId(id, pageable).toList()));
    return responsePostApiList;
  }

  public AbstractResponse addPost(@PathVariable int id, long publishDate,
      RequestPostApi requestPostApi) {
    Person person = getPersonById(id);
    Post post = postMapper.requestPostApiToPost(requestPostApi);
    post.setAuthor(person);
    post.setTime(dateMapper.asLocalDate(publishDate));
    postRepository.save(post);
    return postMapper.postToResponsePostApi(post);
  }

  @Transactional(readOnly = true)
  public List<ResponsePersonApi> searchUser(String firstName, String lastName, int ageFrom,
      int ageTo, String town, int offset, int itemPerPage) {
    int limit = itemPerPage < 1 ? 10 : itemPerPage;
    final PageRequest pag = PageRequest.of(offset / itemPerPage, limit);
    LocalDate startYear = LocalDate.now().minusYears(ageTo + 1);
    LocalDate endYear = LocalDate.now().minusYears(ageFrom);
    Page<Person> allPersons = personRepository
        .findAllByFirstNameContainingAndLastNameContainingAndTownContainingAndBirthDateBetween(
            firstName, lastName, town, startYear, endYear, pag);
    if (allPersons.isEmpty()) {
      throw new EntityNotFoundException("No user found in the database.");
    }
    List<ResponsePersonApi> personList = new ArrayList<>();
    for (Person person : allPersons) {
      personList.add(personMapper.personToPersonApi(person));
    }
    return personList;
  }

  public AbstractResponse blockUser(int id) {
    Person person = getPersonById(id);
    if (person.isBlocked()) {
      throw new EntityNotFoundException("User is already blocked.");
    }
    person.setBlocked(true);
    personRepository.save(person);
    return new ReportApi("Ok");
  }

  public AbstractResponse unlockUser(int id) {
    Person person = getPersonById(id);
    if (!person.isBlocked()) {
      throw new EntityNotFoundException("User is already unlocked.");
    }
    person.setBlocked(false);
    personRepository.save(person);
    return new ReportApi("Ok");
  }

  public void updateActivity(String email) {
    Person person = getPersonByEmail(email);
    person.setLastOnlineTime(LocalDateTime.now());
    personRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Person existPerson = personRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(email));
    return new User(existPerson.getEmail(), existPerson.getPassword(), new ArrayList<>());
  }

  public Person getCurrentPersonByToken(String token) {
    String email = JwtProvider.getUsername(token);
    return personRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("No such user in Person table."));
  }

  public Person getPersonByEmail(String email) {
    return personRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User with this email not found."));
  }

  public Person getPersonById(int personId) {
    return personRepository.findById(personId)
        .orElseThrow(() -> new EntityNotFoundException("User with this ID not found."));
  }
}