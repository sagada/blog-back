package com.my.blog.web.service;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.dto.PostsDto;
import com.my.blog.web.dto.PostsSaveRequestDto;
import com.my.blog.web.persistence.PostsRepository;
import com.my.blog.web.persistence.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public PostsDto create(PostsSaveRequestDto dto)
    {
        Posts posts = PostsSaveRequestDto.from(dto);
        postsRepository.save(posts);
        Posts savedPost = postsRepository.findById(posts.getId()).get();

        return PostsDto.from(savedPost);
    }

    public List<PostsDto> findAll()
    {
        return postsRepository.findAll().stream()
                .map(PostsDto::from)
                .collect(Collectors.toList());
    }

    public PostsDto findById(Long id)
    {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 ID"));

        return PostsDto.from(posts);
    }

    public void deleteById(Long id) {
        postsRepository.deleteById(id);
    }

    @Transactional
    public PostsDto update(PostsDto dto)
    {
        Posts findPosts = postsRepository.findById(dto.getPostsId())
                .orElseThrow(()-> new RuntimeException("없는 ID"));
        findPosts.setContent(dto.getContent());
        findPosts.setTitle(dto.getTitle());

        return PostsDto.from(postsRepository.findById(dto.getPostsId()).get());
    }
}
