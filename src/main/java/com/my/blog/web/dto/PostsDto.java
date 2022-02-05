package com.my.blog.web.dto;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.UserEntity;
import com.my.blog.web.dto.response.ReplyResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class PostsDto {

    private Long postsId;
    private String title;
    private String content;
    private List<ReplyResponse> replyResponseList;
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
        List<ReplyDto> collect = entity.getReplyList().stream().map(ReplyDto::new).collect(Collectors.toList());

        Set<ReplyDto> parentGroup = collect.stream()
                .filter(e -> e.getParentReplyId().equals(0L))
                .collect(Collectors.toSet());

        Map<Long, List<ReplyDto>> childGroup = collect.stream()
                .filter(reply -> !reply.getParentReplyId().equals(0L))
                .collect(Collectors.groupingBy(ReplyDto::getParentReplyId));

        List<ReplyResponse> replyResponses = new ArrayList<>();

        parentGroup.forEach(parent ->
        {
            ReplyResponse replyResponse = new ReplyResponse();

            if (childGroup.containsKey(parent.getReplyId()))
            {
                List<ReplyDto> sortedReplyDtoList = childGroup.get(parent.getReplyId()).stream()
                        .sorted(Comparator.comparing(ReplyDto::getRegDt))
                        .collect(Collectors.toList());

                replyResponse.setChildReplyDtoList(sortedReplyDtoList);
                replyResponse.setParentReplyDto(parent);
            }
            else
            {
                replyResponse.setParentReplyDto(parent);
            }

            replyResponses.add(replyResponse);
        });

        postsDto.setReplyResponseList(replyResponses.stream()
                .sorted(Comparator.comparing(s->s.getParentReplyDto().getReplyId()))
                .collect(Collectors.toList()));

        postsDto.setChgDt(entity.getChgDt());
        postsDto.setRegDt(entity.getRegDt());
        UserEntity userEntity = entity.getUserEntity();
        postsDto.setUserInfo(new UserInfo(userEntity.getUsername(), userEntity.getId()));
        return postsDto;
    }
}
