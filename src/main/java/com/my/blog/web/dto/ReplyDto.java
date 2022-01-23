package com.my.blog.web.dto;

import com.my.blog.web.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDto {
    private String content;

    public static Reply toEntity(ReplyDto dto)
    {
        return new Reply(dto.getContent());
    }
}
