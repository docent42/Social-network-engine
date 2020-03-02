package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
public class ResponseLikeApi extends AbstractResponse {
    private int id;
    private long time;
    @JsonProperty("person_id")
    private Integer personId;
    @JsonProperty("post_id")
    private Integer postId;

    @Data
    @AllArgsConstructor
    public static class Likes extends AbstractResponse {
        private boolean likes;
    }

    @Data
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BitLikes extends AbstractResponse {
        private int likes;
        private Set<Integer> users;
    }
}
