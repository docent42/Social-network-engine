package com.skillbox.sw.controller;

import com.skillbox.sw.api.request.RequestTagApi;
import com.skillbox.sw.api.response.ResponseApi;
import com.skillbox.sw.api.response.TagApi;
import com.skillbox.sw.api.response.TagListApi;
import com.skillbox.sw.service.TagService;
import com.skillbox.sw.util.ObjectsUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tags/")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseApi getAll(@RequestParam(value = "tag", required = false) String searchQuery,
                              @RequestParam(value = "offset", required = false) Integer offset,
                              @RequestParam(value = "itemPerPage", required = false) Integer itemPerPage) {
        return (TagListApi) tagService.getAll(ObjectsUtils.getObjectOrDefaultIfNull(searchQuery, ""),
                PageRequest.of(ObjectsUtils.getObjectOrDefaultIfNull(offset, 0),
                       ObjectsUtils.getObjectOrDefaultIfNull(itemPerPage, 20)));
    }

    @PostMapping
    public ResponseApi save(@RequestBody RequestTagApi requestTagApi) {
        return new ResponseApi<>("string", (TagApi) tagService.save(requestTagApi));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") Integer id) {
        tagService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
