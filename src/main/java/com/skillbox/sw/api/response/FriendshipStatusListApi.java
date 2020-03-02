package com.skillbox.sw.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FriendshipStatusListApi extends AbstractResponse{
  @JsonProperty("data")
  private List<FriendshipStatusApi> friendshipStatusList;
}
