package com.my.blog.web.dto;

import com.my.blog.web.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class PostsDto {
    private Long postsId;
    private String title;
    private String content;
    private List<ReplyDto> replyDtoList;

    private PostsDto(Long postsId, String title, String content)
    {
        this.postsId = postsId;
        this.title = title;
        this.content = content;
    }

    public static Posts toEntity(PostsDto dto)
    {
        return Posts.of(dto.getTitle(), dto.getContent());
    }

    public static PostsDto from(Posts entity)
    {
        PostsDto postsDto = new PostsDto(entity.getId(), entity.getTitle(), entity.getContent());
        postsDto.setReplyDtoList(entity.getReplyList().stream().map(ReplyDto::new).collect(Collectors.toList()));
        return postsDto;
    }
}
