package com.my.blog.domain;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.Reply;
import com.my.blog.web.domain.UserEntity;
import com.my.blog.web.persistence.PostsRepository;
import com.my.blog.web.persistence.ReplyRepository;
import com.my.blog.web.persistence.UserRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PostTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @DisplayName("특정 유저의 포스트 등록 테스트")
    void createPosts()
    {
        UserEntity userEntity = UserEntity.of("username", "email", "password");

        Posts posts = Posts.of("TITLE", "CONTET", userEntity);
        postsRepository.save(posts);

        Posts savedPosts = postsRepository.findById(posts.getId()).orElseThrow(() -> new RuntimeException("없는 포스트"));
        Assertions.assertThat(savedPosts.getContent()).isEqualTo("CONTET");
        UserEntity savedUserEntity = savedPosts.getUserEntity();
        Assertions.assertThat(savedUserEntity.getEmail()).isEqualTo("email");
    }

    @Test
    @Transactional
    @DisplayName("포스트 삭제 테스트")
    void postsDelete()
    {
        UserEntity userEntity = UserEntity.of("username", "email", "password");
        userRepository.save(userEntity);
        Posts posts = Posts.of("TITLE", "CONTENT", userEntity);
        postsRepository.save(posts);

        Reply reply = new Reply();
        reply.setUserEntity(userEntity);
        reply.setContent("CONTENT");
        reply.setParentId(0L);
        posts.setReplyList(Lists.newArrayList(reply));

        Posts savedPosts = postsRepository.findById(posts.getId()).orElseThrow(()-> new RuntimeException(""));
        Assertions.assertThat(savedPosts.getReplyList()).hasSize(1);
        Assertions.assertThat(savedPosts.getReplyList().get(0).getContent()).isEqualTo("CONTENT");
    }

}
