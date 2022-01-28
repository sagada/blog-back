package com.my.blog.web.dto;

import com.my.blog.web.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDto {
    private Long replyId;
    private String content;

    public ReplyDto(Reply reply)
    {
        this.replyId = reply.getId();
        this.content = reply.getContent();
    }

    public static Reply toEntity(ReplyDto dto)
    {
        return new Reply(dto.getContent());
    }
}
