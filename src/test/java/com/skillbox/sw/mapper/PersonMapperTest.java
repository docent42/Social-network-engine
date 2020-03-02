package com.skillbox.sw.mapper;

import com.skillbox.sw.SocialNetworkImplApplication;
import com.skillbox.sw.api.request.RegistrationApi;
import com.skillbox.sw.api.response.ResponsePersonApi;
import com.skillbox.sw.domain.Person;
import com.skillbox.sw.domain.enums.MessagesPermission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test_data")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SocialNetworkImplApplication.class)
class PersonMapperTest {

  @Autowired
  PersonMapper personMapper;

  private static final int ID = 0;
  private static final String FIRST_NAME = "Вася";
  private static final String LAST_NAME = "Пупкин";
  private static final LocalDate REG_DATE = LocalDate.of(2012,1,1);
  private static final LocalDate BIRTH_DATE = LocalDate.of(2012,1,2);
  private static final String EMAIL = "al@cohol.ic";
  private static final int PHONE = 100500;
  private static final String PASSWD = "pwd";
  private static final String PHOTO = "photo.pic";
  private static final String ABOUT = "C001H4CK3R";
  private static final String TOWN = "NY";
  private static final String CONFIRM_CODE = "1234";
  private static final MessagesPermission MSG_PERM = MessagesPermission.ALL;
  private static final LocalDateTime LAST_ONLINE_TIME = LocalDateTime.of(2012, 1, 3, 11,11, 11);
  private static boolean IS_BLOCKED = false;

  private static final long REG_DATE_EPOCH =  1_325_376_000_000L;
  private static final long BIRTH_DATE_EPOCH =  1_325_462_400_000L;
  private static final long LAST_ONLINE_TIME_EPOCH =  1_325_589_071_000L;

  @Test
  void personApiToPerson() {
    Person person = new Person();
    person.setId(ID);
    person.setFirstName(FIRST_NAME);
    person.setLastName(LAST_NAME);
    person.setRegDate(REG_DATE);
    person.setBirthDate(BIRTH_DATE);
    person.setEmail(EMAIL);
    person.setPhone(PHONE);
    person.setPassword(PASSWD);
    person.setPhoto(PHOTO);
    person.setAbout(ABOUT);
    person.setTown(TOWN);
    person.setConfirmationCode(CONFIRM_CODE);
    person.setMessagesPermission(MSG_PERM);
    person.setLastOnlineTime(LAST_ONLINE_TIME);
    person.setBlocked(IS_BLOCKED);

    ResponsePersonApi personApiExpected = ResponsePersonApi.builder()
      .id(ID).firstName(FIRST_NAME).lastName(LAST_NAME)
      .regDate(REG_DATE_EPOCH).birthDate(BIRTH_DATE_EPOCH)
      .email(EMAIL).phone(String.valueOf(PHONE)).photo(PHOTO)
      .about(ABOUT).town(TOWN).messagesPermission(MSG_PERM)
      .lastOnlineTime(LAST_ONLINE_TIME_EPOCH).isBlocked(IS_BLOCKED)
      .build();

    ResponsePersonApi personApiActual = personMapper.personToPersonApi(person);

    assertEquals(personApiExpected, personApiActual);
  }

  @Test
  void registerApiToPerson() {
    RegistrationApi registrationApi = RegistrationApi.builder()
      .email(EMAIL).firstName(FIRST_NAME).lastName(LAST_NAME)
      .passwd1(PASSWD).passwd2(PASSWD).code(Integer.parseInt(CONFIRM_CODE))
      .build();

    Person personExpected = new Person();
    personExpected.setEmail(EMAIL);
    personExpected.setFirstName(FIRST_NAME);
    personExpected.setLastName(LAST_NAME);
    personExpected.setPassword(PASSWD);
    personExpected.setConfirmationCode(CONFIRM_CODE);

    Person personActual = personMapper.registrationApiToPerson(registrationApi);

    assertEquals(personActual, personExpected);
  }

  @Test
  void personToPersonApi() {
  }
}