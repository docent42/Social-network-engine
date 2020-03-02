package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlockHistoryApi extends AbstractResponse {

    private int id;
    private long time;
    @JsonProperty("person_id")
    private Integer personId;
    @JsonProperty("post_id")
    private Integer postId;
    @JsonProperty("comment_id")
    private Integer commentId;
    private Action action;

    public enum Action {BLOCK, UNBLOCK}
}
