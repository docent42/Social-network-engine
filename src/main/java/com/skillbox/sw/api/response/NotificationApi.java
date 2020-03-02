package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationApi extends AbstractResponse {

    private int id;
    @JsonProperty("type_id")
    private Integer typeId;
    @JsonProperty("sent_time")
    private long sentTime;
    @JsonProperty("person_id")
    private Integer personId;
    @JsonProperty("entity_id")
    private int entity;
    private String contact;
    private String info;
}
