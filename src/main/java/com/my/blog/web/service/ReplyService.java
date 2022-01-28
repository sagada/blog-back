package com.my.blog.web.service;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.Reply;
import com.my.blog.web.dto.PostsDto;
import com.my.blog.web.dto.ReplySaveRequestDto;
import com.my.blog.web.persistence.PostsRepository;
import com.my.blog.web.persistence.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public PostsDto addReply(ReplySaveRequestDto dto)
    {
        Posts posts = postsRepository.findById(dto.getPostsId())
                .orElseThrow(() -> new RuntimeException("없는 posts 아이디 입니다."));

        Reply reply = new Reply(dto.getContent());
        reply.setPosts(posts);
        replyRepository.save(reply);

        Posts save = postsRepository.save(posts);
        return PostsDto.from(save);
    }

    public void deleteById(Long replyId)
    {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new RuntimeException("없는 댓글 아이디입니다."));
        replyRepository.deleteById(reply.getId());
    }
}
