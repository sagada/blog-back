package com.my.blog.web.controller;

import com.my.blog.web.domain.UserEntity;
import com.my.blog.web.dto.request.UserLoginRequestDto;
import com.my.blog.web.dto.request.UserSaveRequestDto;
import com.my.blog.web.dto.response.ResponseDto;
import com.my.blog.web.dto.response.UserDto;
import com.my.blog.web.security.TokenProvider;
import com.my.blog.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"유저 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    private final TokenProvider tokenProvider;

    @ApiOperation(value = "회원 가입")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserSaveRequestDto requestDto)
    {
        try {
            UserEntity userEntity = UserEntity.of(
                    requestDto.getUsername(),
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );

            // TODO 회원 가입 DTO
            userEntity.nullId();

            UserEntity registerUser = userService.create(userEntity);

            UserDto userDto = UserDto.builder()
                    .email(registerUser.getEmail())
                    .id(registerUser.getId())
                    .username(registerUser.getUsername())
                    .build();

            return ResponseEntity.ok().body(userDto);
        }catch (Exception e)
        {
            ResponseDto<String> responseDto = ResponseDto.<String>builder()
                    .error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserLoginRequestDto userDto)
    {
        UserEntity userEntity = userService.getByCredentials(
                userDto.getEmail(),
                userDto.getPassword()
        );

        if (userEntity != null)
        {
            final String token = tokenProvider.create(userEntity);

            final UserDto responseUserDto = UserDto.builder()
                    .email(userEntity.getEmail())
                    .token(token)
                    .username(userEntity.getUsername())
                    .id(userEntity.getId())
                    .build();

            return ResponseEntity.ok().body(responseUserDto);
        }
        else
        {
            ResponseDto<String> responseDto = ResponseDto.<String>builder()
                    .error("로그인 에러").build();

            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
