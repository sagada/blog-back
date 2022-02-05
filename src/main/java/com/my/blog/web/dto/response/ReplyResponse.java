package com.my.blog.web.dto.response;

import com.my.blog.web.dto.ReplyDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReplyResponse {
    private ReplyDto parentReplyDto;
    private List<ReplyDto> childReplyDtoList = new ArrayList<>();
}
