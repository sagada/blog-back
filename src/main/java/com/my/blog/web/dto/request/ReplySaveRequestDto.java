package com.my.blog.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplySaveRequestDto {

    private Long postsId;
    private String content;
    private Long parentReplyId;

    public Long getParentReplyId()
    {
        return parentReplyId == null ? null : parentReplyId;
    }
}
