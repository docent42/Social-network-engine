package com.skillbox.sw.mapper;

import com.skillbox.sw.api.request.RegistrationApi;
import com.skillbox.sw.api.request.RequestPersonApi;
import com.skillbox.sw.api.response.ResponsePersonApi;
import com.skillbox.sw.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Mapper(
  uses = {DateMapper.class },
  imports = {
    LocalDate.class,
    ZoneOffset.class
  }
)
public interface PersonMapper extends BaseMapper {

  @Mapping(target = "photo", source = "photoId")
  Person personApiToPerson(RequestPersonApi personApi);

  @Mapping(target = "password", source = "passwd1")
  @Mapping(target = "confirmationCode", source = "code")
  Person registrationApiToPerson(RegistrationApi registrationApi);

  ResponsePersonApi personToPersonApi(Person person);

  @Mapping(target = "photo", source = "photoId")
  ResponsePersonApi requestPersonApiToResponsePersonApi(RequestPersonApi requestPersonApi);
}
