package com.my.blog.domain;

import com.my.blog.web.persistence.PostsRepository;
import com.my.blog.web.persistence.ReplyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PostTest {

    @Autowired
    private ReplyRepository repository;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    @Transactional
    @DisplayName("포스트 등록")
    void createPosts()
    {

    }

}
