package com.skillbox.sw.service;

import com.skillbox.sw.SocialNetworkImplApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test_data")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SocialNetworkImplApplication.class)
public class FriendshipServiceTest {

  @Test
  void findFriends() {
  }

  @Test
  void deleteFriend() {
  }
}
