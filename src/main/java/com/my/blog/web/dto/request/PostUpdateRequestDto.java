package com.my.blog.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String content;
}
