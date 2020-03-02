package com.skillbox.sw.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestNotificationSettingsApi {

    @JsonProperty("notification_type")
    private String notificationType;
    private boolean enable;
}
