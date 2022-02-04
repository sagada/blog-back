package com.my.blog.web.controller;

import com.my.blog.web.dto.request.PostUpdateRequestDto;
import com.my.blog.web.dto.request.PostsSaveRequestDto;
import com.my.blog.web.service.*;
import com.my.blog.web.dto.PostsDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"포스트 컨트롤러"})
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostsController {

    private final PostsService postsService;

    @ApiOperation(value = "게시글 등록")
    @PostMapping
    public PostsDto create(@RequestBody PostsSaveRequestDto dto, @AuthenticationPrincipal String email)
    {
        return postsService.create(dto, email);
    }

    @ApiOperation(value = "게시글 전체 조회")
    @GetMapping
    public List<PostsDto> findAll()
    {
        return postsService.findAll();
    }

    @ApiOperation(value = "게시글 ID 로 조회")
    @GetMapping("/{id}")
    public PostsDto findById(@PathVariable(required = true, value = "id") Long id)
    {
        return postsService.findById(id);
    }

    @ApiOperation(value = "게시글 ID 로 삭제")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true, value = "id") Long id, @AuthenticationPrincipal String email)
    {
        postsService.deleteById(id);
    }

    @ApiOperation(value = "특정 ID 게시글 수정")
    @PutMapping("/{id}")
    public PostsDto update(@RequestBody PostUpdateRequestDto dto, @PathVariable(value = "id") Long id)
    {
        return postsService.update(dto, id);
    }
}
