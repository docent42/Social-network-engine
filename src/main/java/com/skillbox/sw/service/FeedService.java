package com.skillbox.sw.service;

import com.skillbox.sw.api.response.AbstractResponse;
import com.skillbox.sw.api.response.PostListApi;
import com.skillbox.sw.api.response.ResponsePostApi;
import com.skillbox.sw.domain.Person;
import com.skillbox.sw.mapper.PostMapper;
import com.skillbox.sw.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedService {
    @Autowired
    FeedRepository repository;

    @Autowired
    PostMapper postMapper;

    @Autowired
    PersonService personService;

    public AbstractResponse getAllPosts(String token, String name, Integer offset, Integer itemPerPage){
        Person person = personService.getCurrentPersonByToken(token);
        Integer id = person.getId();
        Page<ResponsePostApi> page = repository.getAllPosts(id,name,PageRequest.of(offset,itemPerPage))
                .map(p -> postMapper.postToResponsePostApi(p));
        return new PostListApi(page.toList(),page.getTotalElements(),offset,itemPerPage);
    }
}
