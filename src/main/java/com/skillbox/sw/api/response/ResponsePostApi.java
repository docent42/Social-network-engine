package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePostApi extends AbstractResponse {

    private int id;
    private long time;
    @JsonProperty("author_id")
    private ResponsePersonApi author;
    private String title;
    @JsonProperty("post_text")
    private String postText;
    @JsonProperty("is_blocked")
    private boolean isBlocked;
    private int likes;
    private List<CommentApi> comments;
}
