package com.skillbox.sw.controller;

import com.skillbox.sw.api.response.AbstractResponse;
import com.skillbox.sw.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.skillbox.sw.config.SecurityConstants.HEADER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/feeds")
public class FeedController {

    @Autowired
    FeedService service;

    @GetMapping
    public AbstractResponse getFeeds(
        @RequestHeader(value = HEADER) String token,
        @RequestParam(value = "name", defaultValue = "", required = false) String name,
        @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset,
        @RequestParam(value = "itemPerPage", defaultValue = "20", required = false) Integer itemPerPage){
        return service.getAllPosts(token, name, offset, itemPerPage);
    }
}
