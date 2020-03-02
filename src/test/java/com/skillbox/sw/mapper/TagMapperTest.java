package com.skillbox.sw.mapper;

import com.skillbox.sw.SocialNetworkImplApplication;
import com.skillbox.sw.api.request.RequestTagApi;
import com.skillbox.sw.api.response.TagApi;
import com.skillbox.sw.api.response.TagListApi;
import com.skillbox.sw.domain.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test_data")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SocialNetworkImplApplication.class)
public class TagMapperTest {

    @Autowired
    private TagMapper tagMapper;

    private Tag tag;
    private TagApi tagApi;
    private TagListApi tagListApi;
    private RequestTagApi requestTagApi;


    @BeforeEach
    void setUp() {
        tag = new Tag();
        tag.setId(1);
        tag.setTag("Apple");

        tagApi = new TagApi();
        tagApi.setId(1);
        tagApi.setTag("Hole");
    }

    @Test
    private void shouldMapTagToTagApi() {
        TagApi tagApi = tagMapper.tagToTagApi(tag);
        boolean isTagEquals = tagApi.getTag().equals(tag.getTag());
        boolean isTagIdSame = tagApi.getId() == tag.getId();
        Assertions.assertTrue(isTagEquals &&  isTagIdSame);
    }
}
