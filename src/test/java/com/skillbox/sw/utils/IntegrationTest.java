package com.skillbox.sw.utils;

import com.skillbox.sw.SocialNetworkImplApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test_data")
@ExtendWith(SpringExtension.class)
@Target({ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@AutoConfigureMockMvc
@SpringBootTest(classes = SocialNetworkImplApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public @interface IntegrationTest {
}
