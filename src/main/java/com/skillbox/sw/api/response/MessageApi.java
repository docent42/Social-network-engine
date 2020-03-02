package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skillbox.sw.domain.enums.ReadStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageApi extends AbstractResponse {

    private int id;
    private long time;
    @JsonProperty("author_id")
    private Integer authorId;
    @JsonProperty("recipient_id")
    private Integer recipientId;
    @JsonProperty("message_text")
    private String messageText;
    @JsonProperty("read_status")
    private ReadStatus readStatus;

}
