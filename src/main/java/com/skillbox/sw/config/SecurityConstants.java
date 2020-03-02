package com.skillbox.sw.config;

import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    public static final String API_LOGIN_URL = "/auth/login";
    public static final String HEADER = "Authorization";
    public static final long EXPIRATION_TIME_IN_MILLIS = 3_600_000;
    public static final String SECRET = "supersecret";
    public static final String PREFIX = "skillbox";
}
