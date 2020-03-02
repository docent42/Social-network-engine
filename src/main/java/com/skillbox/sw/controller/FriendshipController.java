package com.skillbox.sw.controller;

import com.skillbox.sw.api.request.RequestDialogUsersApi;
import com.skillbox.sw.api.response.*;
import com.skillbox.sw.domain.Friendship;
import com.skillbox.sw.service.FriendshipService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skillbox.sw.config.SecurityConstants.HEADER;

@RestController
@AllArgsConstructor
public class FriendshipController {

  private FriendshipService friendshipService;

  @GetMapping("/friends")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse getFriends(
      @RequestHeader(value = HEADER) String token,
      @RequestParam(value = "name", defaultValue = "", required = false) String name,
      @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset,
      @RequestParam(value = "itemPerPage", defaultValue = "20", required = false) Integer itemPerPage) {
    Page<ResponsePersonApi> page =
        friendshipService.findFriends(token, name, PageRequest.of(offset, itemPerPage));
    return new PersonListApi(null, page.toList(), page.getTotalElements(), offset, itemPerPage);
  }

  @DeleteMapping("/friends/{id}")
  public AbstractResponse deleteFriend(
      @RequestHeader(value = HEADER) String token,
      @PathVariable(value = "id") int id) {
    List<Friendship> relations = friendshipService.deleteFriend(token, id);
    return new ResponseApi.Message("ok");
  }

  @PostMapping("/friends/{id}")
  public AbstractResponse sendOrApproveRequest(
      @RequestHeader(value = HEADER) String token,
      @PathVariable(value = "id") int id) {
    List<Friendship> relations = friendshipService.sendOrApproveRequest(token, id);
    return new ResponseApi.Message("ok");
  }

  @GetMapping("/friends/request")
  public AbstractResponse getFriendshipRequests(
      @RequestHeader(value = HEADER) String token,
      @RequestParam(value = "name", defaultValue = "", required = false) String name,
      @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset,
      @RequestParam(value = "itemPerPage", defaultValue = "20", required = false) Integer itemPerPage) {
    Page<ResponsePersonApi> page =
        friendshipService.getFriendshipRequests(token, name, PageRequest.of(offset, itemPerPage));
    return new PersonListApi(null, page.toList(), page.getTotalElements(), offset, itemPerPage);
  }

  @GetMapping("/friends/recommendations")
  public AbstractResponse getRecommendations(
      @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset,
      @RequestParam(value = "itemPerPage", defaultValue = "20", required = false) Integer itemPerPage) {
    Page<ResponsePersonApi> page =
        friendshipService.getRecommendations(PageRequest.of(offset, itemPerPage));
    return new PersonListApi("null", page.toList(), page.getTotalElements(), offset, itemPerPage);
  }

  @PostMapping("/is/friends")
  public AbstractResponse checkIfFriends(
      @RequestHeader(value = HEADER) String token,
      @RequestBody RequestDialogUsersApi userIdList) {
    return new FriendshipStatusListApi(friendshipService.checkIfFriends(token, userIdList));
  }
}
