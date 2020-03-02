package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendshipApi extends AbstractResponse {

    private int id;
    @JsonProperty("status_id")
    private Integer statusId;
    @JsonProperty("src_person_id")
    private Integer srcPersonId;
    @JsonProperty("dst_person_id")
    private Integer dstPersonId;
}
