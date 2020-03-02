package com.skillbox.sw.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestTagApi {

    @JsonProperty
    private String tag;
}
