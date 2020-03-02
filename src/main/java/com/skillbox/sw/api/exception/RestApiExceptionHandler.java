package com.skillbox.sw.api.exception;

import com.skillbox.sw.api.response.ErrorApi;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({EntityNotFoundException.class})
  public ErrorApi handleEntityNotFoundException(EntityNotFoundException ex) {
    log.error("Entity exception, bad request.", ex);
    return ErrorApi.builder()
        .error("Invalid request.")
        .errorDescription(ex.getMessage())
        .build();
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler({AuthenticationException.class})
  public ErrorApi userNotAuthorizedException(AuthenticationException ex) {
    log.error("Entity exception, unauthorized.", ex);
    return ErrorApi.builder()
        .error("Invalid request.")
        .errorDescription(ex.getMessage())
        .build();
  }
}