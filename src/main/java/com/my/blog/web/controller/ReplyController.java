package com.my.blog.web.controller;

import com.my.blog.web.dto.PostsDto;
import com.my.blog.web.dto.request.ReplySaveRequestDto;
import com.my.blog.web.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"댓글 컨트롤러"})
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;

    @ApiOperation(value = "댓글 등록", notes = "부모 댓글 없으면 parentReplyId 0번으로 보내면 됩니다.")
    @PostMapping
    public PostsDto addReply(@RequestBody ReplySaveRequestDto dto, @AuthenticationPrincipal String userId)
    {
        return replyService.addReply(dto, Long.parseLong(userId));
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/{replyId}")
    public void deleteReplyById(@PathVariable Long replyId, @AuthenticationPrincipal String  userId)
    {
        replyService.deleteById(replyId, Long.parseLong(userId));
    }
}
