package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentModelApi extends AbstractResponse {

    @JsonProperty("parent_id")
    private int parentId;
    @JsonProperty("comment_text")
    private String commentText;
}
