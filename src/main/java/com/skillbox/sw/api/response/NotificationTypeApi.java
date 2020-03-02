package com.skillbox.sw.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationTypeApi extends AbstractResponse {

    private int id;
    private Code code;
    private String name;

    public enum Code {POST, POST_COMMENT, COMMENT_COMMENT, FRIEND_REQUEST, MESSAGE}
}
