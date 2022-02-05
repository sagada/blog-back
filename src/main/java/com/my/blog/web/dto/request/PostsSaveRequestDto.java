package com.my.blog.web.dto.request;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;


    public PostsSaveRequestDto(String title) {
        this.title = title;
    }

    public static Posts from(PostsSaveRequestDto dto, UserEntity userEntity)
    {
        return Posts.of(dto.getTitle(), dto.getContent(), userEntity);
    }
}
