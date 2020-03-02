package com.skillbox.sw.service;

import com.skillbox.sw.api.request.RequestTagApi;
import com.skillbox.sw.api.response.AbstractResponse;
import com.skillbox.sw.api.response.TagApi;
import com.skillbox.sw.api.response.TagListApi;
import com.skillbox.sw.domain.Tag;
import com.skillbox.sw.mapper.TagMapper;
import com.skillbox.sw.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagService {

    private TagRepository tagRepository;
    private TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public void deleteById(int id) {
        tagRepository.deleteById(id);
    }

    public AbstractResponse getAll(String tag, Pageable pageable) {
        Page<Tag> tagList = tagRepository.findByTagContainingIgnoreCase(tag, pageable);
        List<TagApi> tagApiList = tagList.stream().map(tagMapper::tagToTagApi).collect(Collectors.toList());
        return new TagListApi(tagApiList, tagApiList.size(), (int) pageable.getOffset(), pageable.getPageSize());
    }

    public AbstractResponse save(RequestTagApi requestTagApi) {
        Tag tag = tagMapper.requestTagApiToTag(requestTagApi);
        tag = tagRepository.save(tag);
        return tagMapper.tagToTagApi(tag);
    }
}