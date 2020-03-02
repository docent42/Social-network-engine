package com.skillbox.sw.mapper;

import com.skillbox.sw.SocialNetworkImplApplication;
import com.skillbox.sw.domain.Post;
import com.skillbox.sw.repository.PostLikeRepository;
import com.skillbox.sw.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test_data")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SocialNetworkImplApplication.class)
class PostMapperTest {

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private PostLikeRepository postLikeRepository;

  @Test
  void postToResponsePostApi() {

  }
}