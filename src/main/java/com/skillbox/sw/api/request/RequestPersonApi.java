package com.skillbox.sw.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skillbox.sw.domain.enums.MessagesPermission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Request entity for PUT /users/me
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestPersonApi {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birth_date")
    private long birthDate;
    private String phone;
    @JsonProperty("photo_id")
    private String photoId;
    private String about;
    private String town;
    @JsonProperty("messages_permission")
    @Enumerated(EnumType.STRING)
    private MessagesPermission messagesPermission;
}
