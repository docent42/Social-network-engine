package com.skillbox.sw.controller;

import com.skillbox.sw.api.request.MessageSendRequestBodyApi;
import com.skillbox.sw.api.request.RegistrationApi;
import com.skillbox.sw.api.request.RequestNewEmailApi;
import com.skillbox.sw.api.request.SetPasswordApi;
import com.skillbox.sw.api.response.ReportApi;
import com.skillbox.sw.api.response.ResponseApi;
import com.skillbox.sw.domain.Person;
import com.skillbox.sw.repository.PersonRepository;
import com.skillbox.sw.utils.IntegrationTest;
import com.skillbox.sw.utils.JwtConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@IntegrationTest
public class AccountControllerIntegrationTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PersonRepository personRepository;

    private Person person;
    private RegistrationApi registrationApi;
    private SetPasswordApi setPasswordApi;

    @BeforeEach
    public void init()
    {
        person = Person.builder()
                .id(4)
                .firstName("Garold")
                .lastName("Norman")
                .password("123456789")
                .regDate(LocalDate.now())
                .birthDate(LocalDate.of(1980, 11, 9))
                .email("norm@gmail.com")
                .phone(389104)
                .build();
        personRepository.save(person);

        registrationApi = RegistrationApi
                .builder()
                .email("abc@mail.ru")
                .passwd1("123456")
                .passwd2("123456")
                .firstName("Аркадий")
                .lastName("Петров")
                .code(3675)
                .build();

        setPasswordApi = new SetPasswordApi("123456ABC");
    }

    @Test
    void registration() {
        HttpHeaders headers = getHeaders();
        HttpEntity<RegistrationApi> entity = new HttpEntity<>(registrationApi, headers);
        ResponseEntity<ResponseApi<? extends ReportApi>> response = getExchangeRegistration
                ("/account/register", HttpMethod.POST, entity);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("User successfully aded", response.getBody().getError());
        Assertions.assertEquals("Ok", response.getBody().getData().getMessage());

    }

    @Test
    void changeEmail()
    {
        String newEmail = "arkadiy228@mail.ru";
        HttpHeaders headers = getHeaders();
        RequestNewEmailApi requestNewEmailApi = new RequestNewEmailApi();
        requestNewEmailApi.setEmail(newEmail);

        HttpEntity<RequestNewEmailApi> entity = new HttpEntity<>(requestNewEmailApi, headers);
        ResponseEntity<ResponseApi<? extends ReportApi>> response = getExchangeEmail
                ("/account/email", HttpMethod.PUT, entity);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Your email was successfully changed",
                response.getBody().getError());

        Assertions.assertEquals("Ok", response.getBody().getData().getMessage());

    }

    @Test
    void changePassword()
    {
        HttpHeaders headers = getHeaders();
        HttpEntity<SetPasswordApi> entity = new HttpEntity<>(setPasswordApi, headers);
        ResponseEntity<ResponseApi<? extends ReportApi>> response = getExchangePassword
                ("/account/password/set", HttpMethod.PUT, entity);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Your password was successfully changed", response.getBody().getError());
        Assertions.assertEquals("Ok", response.getBody().getData().getMessage());
    }

    private ResponseEntity<ResponseApi<? extends ReportApi>> getExchangePassword(String url, HttpMethod httpMethod, HttpEntity<SetPasswordApi> entity) {

        return restTemplate
                .exchange(url, httpMethod, entity,
                        new ParameterizedTypeReference<ResponseApi<? extends ReportApi>>() {
                            @Override
                            public Type getType() {
                                return super.getType();
                            }
                        });

    }

    private ResponseEntity<ResponseApi<? extends ReportApi>> getExchangeEmail(String url, HttpMethod httpMethod, HttpEntity<RequestNewEmailApi> entity) {

        return restTemplate
                .exchange(url, httpMethod, entity,
                        new ParameterizedTypeReference<ResponseApi<? extends ReportApi>>() {
                            @Override
                            public Type getType() {
                                return super.getType();
                            }
                        });

    }

    private ResponseEntity getExchangeRegistration(String url, HttpMethod httpMethod, HttpEntity<RegistrationApi> entity) {
        return restTemplate
                .exchange(url, httpMethod, entity,
                        new ParameterizedTypeReference<ResponseApi<? extends ReportApi>>() {
                            @Override
                            public Type getType() {
                                return super.getType();
                            }
                        });
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, JwtConstants.GOOD_TOKEN);
        return headers;
    }

}
