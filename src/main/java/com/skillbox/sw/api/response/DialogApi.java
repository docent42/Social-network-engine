package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogApi extends AbstractResponse {

    private int id;

    @JsonProperty("unread_count")
    private int unreadCount;

    @JsonProperty("last_message")
    private MessageApi lastMessage;
}
