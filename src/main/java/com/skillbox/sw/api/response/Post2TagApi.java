package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post2TagApi extends AbstractResponse {

    private int id;
    @JsonProperty("post_id")
    private int postId;
    @JsonProperty("tag_id")
    private int tagId;
}
