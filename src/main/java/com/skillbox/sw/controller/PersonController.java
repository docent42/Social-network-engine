package com.skillbox.sw.controller;

import static com.skillbox.sw.config.SecurityConstants.HEADER;

import com.skillbox.sw.api.request.RequestPersonApi;
import com.skillbox.sw.api.request.RequestPostApi;
import com.skillbox.sw.api.response.AbstractResponse;
import com.skillbox.sw.api.response.PersonListApi;
import com.skillbox.sw.api.response.PostListApi;
import com.skillbox.sw.api.response.ResponseApi;
import com.skillbox.sw.api.response.ResponsePersonApi;
import com.skillbox.sw.api.response.ResponsePostApi;
import com.skillbox.sw.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class PersonController {

  private PersonService service;

  @Autowired
  public PersonController(PersonService service) {
    this.service = service;
  }

  @GetMapping("/me")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse getCurrentUser(@RequestHeader(value = HEADER) String token) {
    return new ResponseApi<>("Your profile has been successfully received.",
        service.getCurrentUser(token));
  }

  @PutMapping("/me")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse editCurrentUser(@RequestBody RequestPersonApi requestPersonApi,
      @RequestHeader(value = HEADER) String token) {
    return new ResponseApi<>("Your profile has been successfully changed.",
        service.editCurrentUser(token, requestPersonApi));
  }

  @DeleteMapping("/me")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse deleteCurrentUser(@RequestHeader(value = HEADER) String token) {
    return new ResponseApi<>("Your profile has been successfully deleted.",
        service.deleteCurrentUser(token));
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse getUser(@PathVariable int id) {
    return new ResponseApi<>("User found successfully.", service.getUser(id));
  }

  @GetMapping("/{id}/wall")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse getPosts(
      @PathVariable int id,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "itemPerPage", defaultValue = "20") int itemPerPage) {
    List<ResponsePostApi> responsePostApiList = service.getPosts(id, offset, itemPerPage);
    return new PostListApi("Search completed successfully.", responsePostApiList,
        responsePostApiList.size(), offset, itemPerPage);
  }

  @PostMapping("/{id}/wall")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse addPost(@PathVariable int id,
                                  @RequestParam("publish_date") long publishDate,
                                  @RequestBody RequestPostApi requestPostApi) {
    return new ResponseApi<>("Your post has been successfully posted.",
        service.addPost(id, publishDate, requestPostApi));
  }

  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse searchUser(
      @RequestParam(value = "first_name", defaultValue = "") String firstName,
      @RequestParam(value = "last_name", defaultValue = "") String lastName,
      @RequestParam(value = "age_from", defaultValue = "0") int ageFrom,
      @RequestParam(value = "age_to", defaultValue = "100") int ageTo,
      @RequestParam(value = "town", defaultValue = "") String town,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "itemPerPage", defaultValue = "20") int itemPerPage) {
    List<ResponsePersonApi> responsePersonApiList = service.searchUser(firstName, lastName,
        ageFrom, ageTo, town, offset, itemPerPage);
    return new PersonListApi("Search completed successfully.", responsePersonApiList,
        responsePersonApiList.size(), offset, itemPerPage);
  }

  @PutMapping("/block/{id}")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse blockUser(@PathVariable int id) {
    return new ResponseApi<>("User successfully blocked.", service.blockUser(id));
  }

  @DeleteMapping("/block/{id}")
  @ResponseStatus(HttpStatus.OK)
  public AbstractResponse unlockUser(@PathVariable int id) {
    return new ResponseApi<>("User successfully unlocked.", service.unlockUser(id));
  }
}