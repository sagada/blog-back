package com.my.blog.web.dto;

import com.my.blog.web.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReplyDto {

    private Long replyId;
    private String content;
    private Long parentReplyId;
    private LocalDateTime regDt;
    private LocalDateTime chgDt;
    private UserInfo userInfo;

    public ReplyDto(Reply reply)
    {
        this.replyId = reply.getId();
        this.content = reply.getContent();
        this.chgDt = reply.getChgDt();
        this.regDt = reply.getRegDt();
        if (reply.getUserEntity() != null){
        this.userInfo = new UserInfo(reply.getUserEntity().getUsername(), reply.getUserEntity().getId());}
        this.parentReplyId = reply.getParentId();
    }

}
