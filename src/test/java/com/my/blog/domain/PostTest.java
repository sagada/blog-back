package com.my.blog.domain;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.Reply;
import com.my.blog.web.persistence.PostsRepository;
import com.my.blog.web.persistence.ReplyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostTest {

    @Autowired
    private ReplyRepository repository;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    @DisplayName("포스트 등록")
    void createPosts()
    {
        Posts posts = new Posts();
        posts.setContent("Content");
        posts.setTitle("Title");

        postsRepository.save(posts);
        Reply reply = new Reply();
        reply.setContent("Reply Content");
        reply.setPosts(posts);
        repository.save(reply);

        Reply savedReply = repository.findById(reply.getId()).get();
        System.out.println(savedReply);
        System.out.println(savedReply.getPosts());
//        Assertions.assertThat(savedReply.getPosts().getContent()).isEqualTo("Content");
    }
}
