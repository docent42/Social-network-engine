package com.skillbox.sw.service;

import com.skillbox.sw.SocialNetworkImplApplication;
import com.skillbox.sw.api.request.RequestTagApi;
import com.skillbox.sw.api.response.TagApi;
import com.skillbox.sw.api.response.TagListApi;
import com.skillbox.sw.domain.Tag;
import com.skillbox.sw.repository.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ActiveProfiles("test_data")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SocialNetworkImplApplication.class)
public class TagServiceTest {

    // TODO: Дописать тесты

    private Tag tag1;
    private Tag tag2;
    private List<Tag> tagList;
    private Page<Tag> tagPage;

    private RequestTagApi requestTagApi1;
    private RequestTagApi requestTagApi2;

    private TagApi tagApi1;
    private TagApi tagApi2;
    private List<TagApi> tagApiList;
    private TagListApi tagListApi;


    @Autowired
    private TagService tagService;

    @MockBean
    private TagRepository tagRepository;

    @BeforeEach
    void setUp() {
        tag1 = new Tag();
        tag1.setId(1);
        tag1.setTag("Apple");

        tag2 = new Tag();
        tag2.setId(2);
        tag2.setTag("Application");

        tagList = new ArrayList<>();
        tagList.add(tag1);
        tagList.add(tag2);
        tagPage = new PageImpl<Tag>(tagList);

        requestTagApi1 = new RequestTagApi();
        requestTagApi1.setTag("Apple");
        requestTagApi2 = new RequestTagApi();
        requestTagApi2.setTag("Application");

        tagApi1 = new TagApi();
        tagApi1.setId(1);
        tagApi1.setTag("Apple");
        tagApi2 = new TagApi();
        tagApi2.setId(2);
        tagApi2.setTag("Application");
        tagApiList = new ArrayList<>();
        tagApiList.add(tagApi1);
        tagApiList.add(tagApi2);

        tagListApi = new TagListApi(tagApiList, tagApiList.size(), 0, 20);
    }

    @Test
    void saveRequestTagApiAndGetTagApi() {
        Mockito.when(tagRepository.save(any(Tag.class))).thenReturn(tag1);
        TagApi tagApi = (TagApi) tagService.save(requestTagApi1);
        Assertions.assertEquals(requestTagApi1.getTag(), tagApi.getTag());
    }

    @Test
    void searchTagAndGetTagListApi() {
        String searchQuery = "app";
        Mockito.when(tagRepository.findByTagContainingIgnoreCase(anyString(), any(Pageable.class))).thenReturn(tagPage);
        TagListApi tagListApi = (TagListApi) tagService.getAll(searchQuery, PageRequest.of(0, 20));
        Assertions.assertEquals(tagListApi, this.tagListApi);
    }
}

