package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skillbox.sw.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

/**
 * response for PUT /api/v1/dialogs/{id}/users
 */

@Data
@AllArgsConstructor
public class ResponseDialogUsersApi extends AbstractResponse {

    @JsonProperty("user_ids")
    private List<Integer> userIds;

}