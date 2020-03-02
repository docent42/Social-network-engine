package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NotificationListApi extends ResponseApi {

    @JsonProperty("data")
    private List<NotificationApi> notificationList;
    private int total;
    private int offset;
    private int perPage;
}
