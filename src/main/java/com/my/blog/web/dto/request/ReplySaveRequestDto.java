package com.my.blog.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {

    private Long postId;
    private String content;
    private Long parentReplyId;

    public Long getParentReplyId()
    {
        return parentReplyId == null ? null : parentReplyId;
    }
}
