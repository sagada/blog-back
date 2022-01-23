package com.my.blog.web.dto;

import com.my.blog.web.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostsDto {
    private Long id;
    private String title;
    private String content;
//    private List<ReplyDto> replyDtoList;

    private PostsDto(Long id, String title, String content)
    {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static Posts toEntity(PostsDto dto)
    {
        return new Posts(dto.getTitle(), dto.getContent());
    }

    public static PostsDto from(Posts entity)
    {
        return new PostsDto(entity.getId(), entity.getTitle(), entity.getContent());
    }
}
