package com.skillbox.sw.controller;

import com.skillbox.sw.api.request.RegistrationApi;
import com.skillbox.sw.api.request.RequestEmailApi;
import com.skillbox.sw.api.request.RequestNotificationSettingsApi;
import com.skillbox.sw.api.request.SetPasswordApi;
import com.skillbox.sw.api.response.AbstractResponse;
import com.skillbox.sw.api.response.ResponseApi;
import com.skillbox.sw.domain.Person;
import com.skillbox.sw.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.skillbox.sw.config.SecurityConstants.HEADER;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService service;

    @Autowired
    public AccountController(AccountService service)
    {
        this.service = service;
    }
    /* private String token = "skillboxeyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXJha2FuQG1haWwucnUiLCJleHA" +
            "iOjE4OTM0NTYwMDF9.pfKwDjDRMadSuPRPFVaHx35-xersY0GL2UdPmZEjNSpkuX_" +
            "9EOWGQ5C3e7aP6dBg5bixKCxeLobvX2iXgoCFhg";*/

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse add(@RequestBody RegistrationApi account) //???
    {
        return service.save(account);
    }

    @PutMapping("/password/recovery")
    public void sendCodeOnEmail(String email)
    {
        // send code for reset on email
    }

    @PutMapping("/password/set")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse changePassword(@RequestHeader(value = HEADER) String token,
                                           @RequestBody SetPasswordApi setPasswordApi)
    {
        return service.changePassword(token, setPasswordApi.getPassword());
    }

    @PutMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse changeEmail(@RequestHeader(value = HEADER) String token,
                                        @RequestBody RequestEmailApi requestEmailApi)
    {
        return service.changeEmail(token, requestEmailApi.getEmail());
    }

    @PutMapping("/notifications")
    @ResponseStatus(HttpStatus.OK)
    public AbstractResponse changeSettingsNotifications(@RequestHeader(value = HEADER) String token,
                                                        @RequestBody RequestNotificationSettingsApi requestNotificationSettingsApi)
    {
        return new ResponseApi<>("Your settings notifications was successfully changed",
                service.changeNotifications(token, requestNotificationSettingsApi));
    }







}
