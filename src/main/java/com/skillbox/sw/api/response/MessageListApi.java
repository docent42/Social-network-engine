package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageListApi extends ResponseApi{

    @JsonProperty("data")
    private List<MessageApi> messageList;
    private long total;
    private int offset;
    private int perPage;

    public MessageListApi(String error, List<MessageApi> messageList, int total, int offset,
                         int perPage) {
        super(error);
        this.messageList = messageList;
        this.total = total;
        this.offset = offset;
        this.perPage = perPage;
    }

}
