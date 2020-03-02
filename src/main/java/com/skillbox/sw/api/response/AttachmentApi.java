package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttachmentApi extends AbstractResponse {

    private int id;
    @JsonProperty("post_id")
    private String postId;
    private String name;
    private String path;
}
