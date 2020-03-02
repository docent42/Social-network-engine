package com.skillbox.sw.eventhandler;

import com.skillbox.sw.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.access.event.AuthorizedEvent;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthorizedHandler implements ApplicationListener<AuthorizedEvent> {

  @Autowired
  PersonService personService;

  @Override
  public void onApplicationEvent(AuthorizedEvent authorizedEvent) {

    if (authorizedEvent.getAuthentication() instanceof AnonymousAuthenticationToken)
      return;
    personService.updateActivity(authorizedEvent.getAuthentication().getName());
  }
}
