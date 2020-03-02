package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationBaseApi extends AbstractResponse {

    private int id;
    @JsonProperty("type_id")
    private int typeId;
    @JsonProperty("sent_time")
    private long sentTime;
    @JsonProperty("entity_id")
    private int entity;
    private String info;
}
