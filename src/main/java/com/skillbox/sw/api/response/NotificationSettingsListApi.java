package com.skillbox.sw.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NotificationSettingsListApi extends AbstractResponse {

    private String error;
    private long timestamp;
    private List<ResponseNotificationSettingsApi> data;
}
