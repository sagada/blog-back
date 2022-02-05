package com.my.blog.web.service;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.Reply;
import com.my.blog.web.domain.UserEntity;
import com.my.blog.web.dto.PostsDto;
import com.my.blog.web.dto.messge.ErrorType;
import com.my.blog.web.dto.request.ReplySaveRequestDto;
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
    private final UserService userService;

    @Transactional
    public PostsDto addReply(ReplySaveRequestDto dto)
    {
        Posts posts = postsRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("없는 posts 아이디 입니다."));

        Long userId = AuthUtil.getCurrentUserId();
        UserEntity userEntity = userService.findById(userId);
        Reply reply = new Reply(dto.getContent());
        reply.setPosts(posts);
        reply.setUserEntity(userEntity);
        reply.setParentId(dto.getParentReplyId());

        replyRepository.save(reply);

        Posts save = postsRepository.save(posts);
        return PostsDto.from(save);
    }

    public void deleteById(Long replyId, long userId)
    {
        UserEntity userEntity = userService.findById(userId);
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new RuntimeException("없는 댓글 아이디입니다."));
        if (userEntity.getId().equals(reply.getUserEntity().getId()))
        {
            replyRepository.deleteById(reply.getId());
        }
        else
        {
            throw new RuntimeException(ErrorType.NONE_AUTHORITY.getMessage());
        }
    }
}
