package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentApi extends AbstractResponse {

    private int id;
    private long time;
    @JsonProperty("post_id")
    private Integer postId;
    @JsonProperty("parent_id")
    private Integer parentId;

    @JsonProperty("author_id")
    private Integer authorId;
    @JsonProperty("comment_text")
    private String commentText;
    @JsonProperty("is_blocked")
    private boolean isBlocked;
}
