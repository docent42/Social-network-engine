package com.skillbox.sw.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillbox.sw.config.SecurityConstants;
import com.skillbox.sw.domain.Person;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setFilterProcessesUrl(SecurityConstants.API_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            Person cred = new ObjectMapper()
                    .readValue(request.getInputStream(), Person.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    cred.getEmail(),
                    cred.getPassword(),
                    new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JwtProvider.createToken(((User) authResult.getPrincipal()).getUsername(), new ArrayList<>());
        response.addHeader(SecurityConstants.HEADER, SecurityConstants.PREFIX + token);
    }
}
