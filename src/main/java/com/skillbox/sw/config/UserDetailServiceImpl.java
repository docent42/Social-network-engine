package com.skillbox.sw.config;

import com.skillbox.sw.domain.Person;
import com.skillbox.sw.repository.PersonRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private PersonRepository testUserRepository;

    public UserDetailServiceImpl(PersonRepository testUserRepository) {
        this.testUserRepository = testUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person existPerson = testUserRepository.findByEmail(email).get();
        if (existPerson == null){
            throw new UsernameNotFoundException(email);
        }
        return new User(existPerson.getEmail(),existPerson.getPassword()
        , new ArrayList<>());
    }
}
