package com.skillbox.sw.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skillbox.sw.api.response.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageSendRequestBodyApi extends AbstractResponse {

    @JsonProperty("message_text")
    private String messageText;
}