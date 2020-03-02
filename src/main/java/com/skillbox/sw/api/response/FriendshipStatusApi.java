package com.skillbox.sw.api.response;

import com.skillbox.sw.domain.enums.FriendshipCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendshipStatusApi extends AbstractResponse {

    private int id;
    private FriendshipCode code;
}
