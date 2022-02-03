package com.my.blog.web.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String token;
    private String email;
    private String username;
    private String password;
    private Long id;
}
