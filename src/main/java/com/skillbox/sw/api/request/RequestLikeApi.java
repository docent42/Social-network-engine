package com.skillbox.sw.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestLikeApi {

    @JsonProperty("item_id")
    private int itemId;
    private String type;
}
