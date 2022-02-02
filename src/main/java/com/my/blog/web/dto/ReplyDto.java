package com.my.blog.web.dto;

import com.my.blog.web.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDto {
    private Long replyId;
    private String content;
    private Long parentReplyId;
    private LocalDateTime regDt;
    private LocalDateTime chgDt;

    public ReplyDto(Reply reply)
    {
        this.replyId = reply.getId();
        this.content = reply.getContent();
        this.chgDt = reply.getChgDt();
        this.regDt = reply.getRegDt();
        this.parentReplyId = reply.getParentId();
    }

}
