package com.my.blog.web.service;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.Reply;
import com.my.blog.web.dto.ReplyDto;
import com.my.blog.web.persistence.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository repository;

    @Transactional
    public void createReplyList(List<ReplyDto> dto, Posts posts)
    {
        List<Reply> collect = dto.stream().map(ReplyDto::toEntity).collect(Collectors.toList());
        repository.saveAll(collect);
        collect.forEach(entity -> entity.setPosts(posts));
    }
}
