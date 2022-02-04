package com.my.blog.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveRequestDto {
    private String email;
    private String username;
    private String password;
}
