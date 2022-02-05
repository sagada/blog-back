package com.my.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.blog.web.domain.UserEntity;
import com.my.blog.web.dto.request.PostsSaveRequestDto;
import com.my.blog.web.persistence.UserRepository;
import com.my.blog.web.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostsControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    public void init()
    {
        userService.create(UserEntity.of("_username", "email@naver.com", "pass"));
    }

    @Test
    @DisplayName("포스트 생성 컨트롤러 테스트")
    public void postsSaveTest() throws Exception
    {
        PostsSaveRequestDto postsSaveRequestDto = new PostsSaveRequestDto();
        postsSaveRequestDto.setContent("Content");
        postsSaveRequestDto.setTitle("Title");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(postsSaveRequestDto))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
