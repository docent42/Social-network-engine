package com.skillbox.sw.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestPostApi {

    private String title;
    @JsonProperty("post_text")
    private String postText;
}
