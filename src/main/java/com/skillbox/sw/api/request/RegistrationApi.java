package com.skillbox.sw.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Request entity for POST /account/register
 */

@Data
@Builder
@AllArgsConstructor
public class RegistrationApi {

    private String email;
    private String passwd1;
    private String passwd2;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private int code;
}
