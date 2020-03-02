package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonEditBodyApi extends AbstractResponse {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birth_date")
    private long birthDate;
    private String phone;
    @JsonProperty("photo_id")
    private String photoId;
    private String about;
    @JsonProperty("town_id")
    private int townId;
    @JsonProperty("country_id")
    private int countryId;
    @JsonProperty("messages_permission")
    private MessagesPermission messagesPermission;

    public enum MessagesPermission {ALL, FRIENDS}
}
