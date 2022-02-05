package com.my.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.blog.web.dto.response.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("유저 회원 가입후에 정상 로그인 테스트")
    public void userLoginTest() throws Exception
    {
        // given
        final String username = "saga1";
        final String email = "email1.com";
        final String password = "password1";

        final UserDto userDto = UserDto.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(userDto))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        // then
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(userDto))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.password").isEmpty());
    }

    @Test
    @DisplayName("로그인 비밀번호 에러 테스트")
    public void loginPasswordErrorTest() throws Exception
    {
        // given
        final String username = "saga2";
        final String email = "email.com2";
        final String password = "password2";

        final UserDto userDto = UserDto.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();

        // when
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(userDto))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        userDto.setPassword("error password");

        // then
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(userDto))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

}
