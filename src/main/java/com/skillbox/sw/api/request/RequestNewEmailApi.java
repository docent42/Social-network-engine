package com.skillbox.sw.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestNewEmailApi {

    @JsonProperty
    private String email;

}
