package com.my.blog.web.service;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.UserEntity;
import com.my.blog.web.dto.request.PostUpdateRequestDto;
import com.my.blog.web.dto.PostsDto;
import com.my.blog.web.dto.request.PostsSaveRequestDto;
import com.my.blog.web.persistence.PostsRepository;
import com.my.blog.web.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostsDto create(PostsSaveRequestDto dto, String email)
    {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new RuntimeException("없는 유저!");

        Posts posts = PostsSaveRequestDto.from(dto, userEntity);
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
    public PostsDto update(PostUpdateRequestDto dto, Long postId)
    {
        Posts findPosts = postsRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException("없는 ID"));
        findPosts.setContent(dto.getContent());
        findPosts.setTitle(dto.getTitle());
        postsRepository.save(findPosts);
        return PostsDto.from(postsRepository.findById(postId).get());
    }
}
