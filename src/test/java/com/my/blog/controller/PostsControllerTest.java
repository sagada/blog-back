package com.my.blog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PostsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("포스트 생성 컨트롤러 테스트")
    public void postsSaveTest()
    {

    }
}
