package com.my.blog.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplySaveRequestDto {

    private Long postsId;
    private String content;
}
