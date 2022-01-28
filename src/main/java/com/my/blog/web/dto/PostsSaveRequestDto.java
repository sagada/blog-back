package com.my.blog.web.dto;

import com.my.blog.web.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;

    public static Posts from(PostsSaveRequestDto dto)
    {
        return Posts.of(dto.getTitle(), dto.getContent());
    }
}
