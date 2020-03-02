package com.skillbox.sw.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostCommentApi {

    @JsonProperty("parent_id")
    private int parentId;
    @JsonProperty("comment_text")
    private String commentText;
}
