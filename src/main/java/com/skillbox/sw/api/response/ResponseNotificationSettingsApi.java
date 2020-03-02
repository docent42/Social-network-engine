package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseNotificationSettingsApi extends AbstractResponse {
    @JsonProperty("notification_type")
    private String notificationType;
    private boolean enable;
}
