package com.skillbox.sw.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

  public long asEpochMillis(LocalDate localDate) {
    return localDate != null
      ? localDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond() * 1000
      : 0;
  }
  public long asEpochMillis(LocalDateTime dateTime) {
    return dateTime != null
      ? dateTime.atOffset(ZoneOffset.UTC).toEpochSecond() * 1000
      : 0;
  }

  public LocalDate asLocalDate(long epochMillis) {
    return LocalDate.ofEpochDay(epochMillis/86_400_000);
  }

  public LocalDateTime asLocalDateTime(long epochMillis) {
    return LocalDateTime.ofEpochSecond(epochMillis/1000, 0, ZoneOffset.UTC);
  }
}
