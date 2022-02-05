package com.my.blog.web.service;

import com.my.blog.web.domain.UserEntity;
import com.my.blog.web.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class MockUserInitialService {

    private final UserService userService;

    @PostConstruct
    public void init()
    {
        userService.create(UserEntity.of("master", "master", "master"));
    }
}
