package com.my.blog.web.dto;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private UserInfo userInfo;

    private LocalDateTime regDt;
    private LocalDateTime chgDt;

    private PostsDto(Long postsId, String title, String content)
    {
        this.postsId = postsId;
        this.title = title;
        this.content = content;
    }

    public static PostsDto from(Posts entity)
    {
        PostsDto postsDto = new PostsDto(entity.getId(), entity.getTitle(), entity.getContent());
        postsDto.setReplyDtoList(entity.getReplyList().stream().map(ReplyDto::new).collect(Collectors.toList()));
        postsDto.setChgDt(entity.getChgDt());
        postsDto.setRegDt(entity.getRegDt());
        UserEntity userEntity = entity.getUserEntity();
        postsDto.setUserInfo(new UserInfo(userEntity.getUsername(), userEntity.getId()));
        return postsDto;
    }
}
