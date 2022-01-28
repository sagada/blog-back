package com.my.blog.web.controller;

import com.my.blog.web.dto.PostsDto;
import com.my.blog.web.dto.ReplySaveRequestDto;
import com.my.blog.web.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"reply 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;

    @ApiOperation(value = "댓글 등록")
    @PostMapping
    public PostsDto addReply(@RequestBody ReplySaveRequestDto dto)
    {
        return replyService.addReply(dto);
    }
    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/{replyId}")
    public void deleteReplyById(@PathVariable Long replyId)
    {
        replyService.deleteById(replyId);
    }
}
