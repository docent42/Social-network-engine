package com.skillbox.sw.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.skillbox.sw.api.request.RequestPersonApi;
import com.skillbox.sw.api.request.RequestPostApi;
import com.skillbox.sw.api.response.CommentApi;
import com.skillbox.sw.api.response.PersonListApi;
import com.skillbox.sw.api.response.PostListApi;
import com.skillbox.sw.api.response.ReportApi;
import com.skillbox.sw.api.response.ResponseApi;
import com.skillbox.sw.api.response.ResponsePersonApi;
import com.skillbox.sw.api.response.ResponsePostApi;
import com.skillbox.sw.domain.Person;
import com.skillbox.sw.domain.Post;
import com.skillbox.sw.domain.enums.MessagesPermission;
import com.skillbox.sw.mapper.DateMapper;
import com.skillbox.sw.mapper.PersonMapper;
import com.skillbox.sw.mapper.PostMapper;
import com.skillbox.sw.repository.PersonRepository;
import com.skillbox.sw.repository.PostRepository;
import com.skillbox.sw.utils.IntegrationTest;
import com.skillbox.sw.utils.JwtConstants;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@IntegrationTest
class PersonControllerIntegrationTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  DateMapper dateMapper;

  @Autowired
  PostMapper postMapper;

  @Autowired
  PersonMapper personMapper;

  private static Person person;
  private static RequestPersonApi requestPersonApi;
  private static ResponsePersonApi responsePersonApi;
  private static RequestPostApi requestPostApi;
  private static Person blockedPerson;
  private static Person unlockedPerson;
  private static Person postPerson;
  private static Person blockedUser;
  private static Person unlockedUser;
  private static Person person1;
  private static Person person2;

  @BeforeEach
  public void init() {
    person = Person.builder()
        .id(1)
        .firstName("Вася")
        .lastName("Пупкин")
        .birthDate(LocalDate.of(1982, 11, 9))
        .regDate(LocalDate.now())
        .email(JwtConstants.EMAIL)
        .password("123456789")
        .phone(123456)
        .lastOnlineTime(LocalDateTime.now())
        .photo("pic")
        .build();
    personRepository.save(person);

    requestPersonApi = RequestPersonApi.builder()
        .firstName("Henri")
        .lastName("Larson")
        .birthDate(dateMapper.asEpochMillis(LocalDate.of(1986, 8, 26)))
        .phone("5307192")
        .photoId("rw39q1ewf")
        .about("Eat sad people")
        .town("Duisburg")
        .messagesPermission(MessagesPermission.ALL)
        .build();

    responsePersonApi = ResponsePersonApi.builder()
        .id(1)
        .firstName("Вася")
        .lastName("Пупкин")
        .birthDate(dateMapper.asEpochMillis(LocalDate.of(1982, 11, 9)))
        .regDate(dateMapper.asEpochMillis(LocalDate.now()))
        .email(JwtConstants.EMAIL)
        .phone("123456")
        .lastOnlineTime(dateMapper.asEpochMillis(person.getLastOnlineTime()))
        .photo("pic")
        .build();

    requestPostApi = RequestPostApi.builder()
        .title("Теория вероятностей")
        .postText("Раздел математики, изучающий случайные события, случайные величины, их свойства "
            + "и операции над ними.")
        .build();

    blockedPerson = Person.builder()
        .id(2)
        .firstName("Ben")
        .lastName("Gash")
        .password("123456789")
        .birthDate(LocalDate.of(1987, 1, 3))
        .town("Denver")
        .isBlocked(true)
        .build();
    personRepository.save(blockedPerson);

    unlockedPerson = Person.builder()
        .id(3)
        .firstName("Many")
        .lastName("Bishop")
        .password("123456789")
        .isBlocked(false)
        .birthDate(LocalDate.of(1990, 4, 9))
        .town("Denver")
        .build();
    personRepository.save(unlockedPerson);

    postPerson = Person.builder()
        .id(4)
        .firstName("Garold")
        .lastName("Norman")
        .password("123456789")
        .regDate(LocalDate.now())
        .birthDate(LocalDate.of(1980, 11, 9))
        .email("norm@gmail.com")
        .phone(389104)
        .build();
    personRepository.save(postPerson);

    blockedUser = Person.builder()
        .id(5)
        .firstName("William")
        .lastName("More")
        .password("123456789")
        .isBlocked(true)
        .birthDate(LocalDate.of(1987, 1, 3))
        .build();
    personRepository.save(blockedUser);

    unlockedUser = Person.builder()
        .id(6)
        .firstName("Sylvester")
        .lastName("Larson")
        .password("123456789")
        .birthDate(LocalDate.of(1987, 1, 3))
        .build();
    personRepository.save(unlockedUser);

    person1 = Person.builder()
        .id(7)
        .firstName("Johnny")
        .lastName("Sins")
        .password("123456789")
        .regDate(LocalDate.now())
        .birthDate(LocalDate.of(1988, 5, 4))
        .town("New York")
        .build();
    personRepository.save(person1);

    person2 = Person.builder()
        .id(8)
        .firstName("Johnny")
        .lastName("Sins")
        .password("123456789")
        .regDate(LocalDate.now())
        .birthDate(LocalDate.of(1988, 8, 1))
        .town("New York2")
        .build();
    personRepository.save(person2);
  }

  @Test
  void getCurrentUser_resultOk() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<ResponseApi<? extends ResponsePersonApi>> response = getExchangePerson
        ("/users/me", HttpMethod.GET, entity);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("Your profile has been successfully received.",
        response.getBody().getError());
    Assertions.assertEquals(responsePersonApi, response.getBody().getData());
  }

  @Test
  void getCurrentUser_withBadToken_resultUnauthorized() {
    HttpHeaders headers = getHeadersWithBadToken();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<Object> response = restTemplate.exchange("/users/me",
        HttpMethod.GET, entity, Object.class);

    Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
  }

  @Test
  void editCurrentUser_resultOk() {
    ResponsePersonApi newResponsePersonApi = personMapper
        .requestPersonApiToResponsePersonApi(requestPersonApi);
    newResponsePersonApi.setId(1);
    newResponsePersonApi.setEmail("tarakan@mail.ru");

    HttpHeaders headers = getHeaders();
    HttpEntity<RequestPersonApi> entity = new HttpEntity<>(requestPersonApi, headers);
    ResponseEntity<ResponseApi<? extends ResponsePersonApi>> response =
        getExchangeEditPerson("/users/me", HttpMethod.PUT, entity);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("Your profile has been successfully changed.",
        response.getBody().getError());
    Assertions.assertEquals(newResponsePersonApi, response.getBody().getData());
  }

  @Test
  void deleteCurrentUser_resultOk() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<ResponseApi<? extends ReportApi>> response =
        getExchangeMessage("/users/me", HttpMethod.DELETE, entity);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("Your profile has been successfully deleted.",
        response.getBody().getError());
    Assertions.assertEquals("Ok", response.getBody().getData().getMessage());
  }

  @Test
  void getUser_resultOk() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<ResponseApi<? extends ResponsePersonApi>> response = getExchangePerson
        ("/users/1", HttpMethod.GET, entity);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("User found successfully.", response.getBody().getError());
    Assertions.assertEquals(responsePersonApi, response.getBody().getData());
  }

  @Test
  void getUser_withNonexistentUser_resultBadRequest() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<Object> response = restTemplate
        .exchange("/users/100", HttpMethod.GET, entity, Object.class);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void getPosts_resultOk() {
    Post post1 = Post.builder()
        .title("Сетевой уровень")
        .postText("модели предназначен для определения пути передачи данных.")
        .author(postPerson)
        .build();
    postRepository.save(post1);

    Post post2 = Post.builder()
        .title("Протоколы сетевого уровня")
        .postText("маршрутизируют данные от источника к получателю.")
        .author(postPerson)
        .build();
    postRepository.save(post2);

    List<ResponsePostApi> postList = new ArrayList<>();

    ResponsePostApi responsePostApi1 = postMapper.postToResponsePostApi(post1);
    List<CommentApi> listComment1 = new ArrayList<>();
    responsePostApi1.setComments(listComment1);
    postList.add(responsePostApi1);

    ResponsePostApi responsePostApi2 = postMapper.postToResponsePostApi(post2);
    List<CommentApi> listComment2 = new ArrayList<>();
    responsePostApi2.setComments(listComment2);
    postList.add(responsePostApi2);

    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);

    ParameterizedTypeReference<PostListApi> listParameterizedTypeReference =
        new ParameterizedTypeReference<PostListApi>() {
          @Override
          public Type getType() {
            return super.getType();
          }
        };

    ResponseEntity<PostListApi> response = restTemplate
        .exchange("/users/4/wall?offset=0&itemPerPage=5", HttpMethod.GET, entity,
            listParameterizedTypeReference);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("Search completed successfully.",
        response.getBody().getError());
    Assertions.assertEquals(2, response.getBody().getTotal());
    Assertions.assertEquals(postList, response.getBody().getPostList());
  }

  @Test
  void getPosts_withNonexistentUser_resultBadRequest() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<Object> response = restTemplate
        .exchange("/users/10/wall?offset=0&itemPerPage=5",
            HttpMethod.GET, entity, Object.class);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void getPosts_withUserWithoutPosts_resultBadRequest() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<Object> response = restTemplate
        .exchange("/users/2/wall?offset=0&itemPerPage=5",
            HttpMethod.GET, entity, Object.class);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void addPost_resultOk() {
    ResponsePostApi responsePostApi = ResponsePostApi.builder()
//        Don't know how to fix this shit with id nicer
        .id((int) (postRepository.count() + 1))
        .time(dateMapper.asEpochMillis(LocalDate.now()))
        .title("Теория вероятностей")
        .author(personMapper.personToPersonApi(person))
        .postText("Раздел математики, изучающий случайные события, случайные величины, их свойства "
            + "и операции над ними.")
        .build();

    HttpHeaders headers = getHeaders();
    HttpEntity<RequestPostApi> entity = new HttpEntity<>(requestPostApi, headers);
    ResponseEntity<ResponseApi<? extends ResponsePostApi>> response =
        getExchangePost("/users/1/wall?publish_date=15151518", HttpMethod.POST, entity);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("Your post has been successfully posted.",
        response.getBody().getError());
    Assertions.assertEquals(responsePostApi, response.getBody().getData());
  }

  @Test
  void addPost_withNonexistentUser_resultBadRequest() {
    HttpHeaders headers = getHeaders();
    HttpEntity<RequestPostApi> entity = new HttpEntity<>(requestPostApi, headers);
    ResponseEntity<Object> response = restTemplate
        .exchange("/users/100/wall?publish_date=15151518",
            HttpMethod.POST, entity, Object.class);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void searchUser_resultOk() {
    ResponsePersonApi person3 = personMapper.personToPersonApi(person1);
    ResponsePersonApi person4 = personMapper.personToPersonApi(person2);

    List<ResponsePersonApi> personList = new ArrayList<>();
    personList.add(person3);
    personList.add(person4);

    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);

    ParameterizedTypeReference<PersonListApi> parameterizedTypeReference =
        new ParameterizedTypeReference<PersonListApi>() {
          @Override
          public Type getType() {
            return super.getType();
          }
        };

    ResponseEntity<PersonListApi> response = restTemplate
        .exchange("/users/search?first_name=Johnny&age_from=18&age_to=49&town"
                + "=&offset=1&itemPerPage=2", HttpMethod.GET, entity,
            parameterizedTypeReference);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("Search completed successfully.",
        response.getBody().getError());
    Assertions.assertEquals(personList, response.getBody().getPersonList());
    Assertions.assertEquals(2, response.getBody().getTotal());
  }

  @Test
  void searchUser_withUniqueParameters_resultBadRequest() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<Object> response = restTemplate
        .exchange("/users/search?first_name=Karma&last_name=Fischer&age_from=18&age_to=40&town"
            + "=Leipzig&offset=0&itemPerPage=20", HttpMethod.GET, entity, Object.class);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void blockUser_resultOk() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<ResponseApi<? extends ReportApi>> response =
        getExchangeMessage("/users/block/3", HttpMethod.PUT, entity);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("User successfully blocked.", response.getBody().getError());
    Assertions.assertEquals("Ok", response.getBody().getData().getMessage());
  }

  @Test
  void blockUser_withNonexistentUser_resultBadRequest() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<Object> response = restTemplate
        .exchange("/users/block/100", HttpMethod.PUT, entity, Object.class);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void blockUser_withBlockedUser_resultBadRequest() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<Object> response = restTemplate
        .exchange("/users/block/5", HttpMethod.PUT, entity, Object.class);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void unlockUser_resultOk() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<ResponseApi<? extends ReportApi>> response =
        getExchangeMessage("/users/block/2", HttpMethod.DELETE, entity);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("User successfully unlocked.", response.getBody().getError());
    Assertions.assertEquals("Ok", response.getBody().getData().getMessage());
  }

  @Test
  void unlockUser_withNonexistentUser_resultBadRequest() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<Object> response = restTemplate
        .exchange("/users/block/10", HttpMethod.DELETE, entity, Object.class);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void unlockUser_withUnlockedUser_resultBadRequest() {
    HttpHeaders headers = getHeaders();
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<Object> response = restTemplate
        .exchange("/users/block/6", HttpMethod.DELETE, entity, Object.class);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  private ResponseEntity<ResponseApi<? extends ResponsePersonApi>> getExchangePerson
      (String url, HttpMethod HttpMethod, HttpEntity<String> entity) {
    return restTemplate.
        exchange(url, HttpMethod, entity,
            new ParameterizedTypeReference<ResponseApi<? extends ResponsePersonApi>>() {
              @Override
              public Type getType() {
                return super.getType();
              }
            });
  }

  private ResponseEntity<ResponseApi<? extends ReportApi>> getExchangeMessage
      (String url, HttpMethod HttpMethod, HttpEntity<String> entity) {
    return restTemplate.
        exchange(url, HttpMethod, entity,
            new ParameterizedTypeReference<ResponseApi<? extends ReportApi>>() {
              @Override
              public Type getType() {
                return super.getType();
              }
            });
  }

  private ResponseEntity<ResponseApi<? extends ResponsePostApi>> getExchangePost
      (String url, HttpMethod HttpMethod, HttpEntity<RequestPostApi> entity) {
    return restTemplate.
        exchange(url, HttpMethod, entity,
            new ParameterizedTypeReference<ResponseApi<? extends ResponsePostApi>>() {
              @Override
              public Type getType() {
                return super.getType();
              }
            });
  }

  private ResponseEntity<ResponseApi<? extends ResponsePersonApi>> getExchangeEditPerson
      (String url, HttpMethod HttpMethod, HttpEntity<RequestPersonApi> entity) {
    return restTemplate.
        exchange(url, HttpMethod, entity,
            new ParameterizedTypeReference<ResponseApi<? extends ResponsePersonApi>>() {
              @Override
              public Type getType() {
                return super.getType();
              }
            });
  }

  public static HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTHORIZATION, JwtConstants.GOOD_TOKEN);
    return headers;
  }

  public static HttpHeaders getHeadersWithBadToken() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTHORIZATION, JwtConstants.BAD_TOKEN);
    return headers;
  }
}