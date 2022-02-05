package com.my.blog.web.service;

import com.my.blog.web.domain.Posts;
import com.my.blog.web.domain.UserEntity;
import com.my.blog.web.dto.PostsDto;
import com.my.blog.web.dto.messge.ErrorType;
import com.my.blog.web.dto.request.PostUpdateRequestDto;
import com.my.blog.web.dto.request.PostsSaveRequestDto;
import com.my.blog.web.persistence.PostsRepository;
import com.my.blog.web.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserService userService;

    @Transactional
    public PostsDto create(PostsSaveRequestDto dto)
    {
        Long userId = AuthUtil.getCurrentUserId();
        UserEntity userEntity = userService.findById(userId);

        Posts posts = PostsSaveRequestDto.from(dto, userEntity);
        postsRepository.save(posts);

        Posts savedPost = postsRepository.findById(posts.getId())
                .orElseThrow(() -> new RuntimeException(ErrorType.NONE_POST.getMessage())
        );

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

    public void deleteById(Long postId)
    {
        Long userId = AuthUtil.getCurrentUserId();

        Posts findPosts = postsRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException(ErrorType.NONE_POST.name()));

        UserEntity userEntity = userService.findById(userId);

        if (findPosts.getUserEntity().getId().equals(userEntity.getId()))
        {
            postsRepository.delete(findPosts);
        }
        else
        {
            throw new RuntimeException(ErrorType.NONE_AUTHORITY.getMessage());
        }
    }

    public PostsDto update(PostUpdateRequestDto dto, Long postId)
    {
        Long userId = AuthUtil.getCurrentUserId();

        Posts findPosts = postsRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("없는 ID"));

        UserEntity userEntity = userService.findById(userId);

        if (!findPosts.getUserEntity().equals(userEntity))
        {
            throw new RuntimeException(ErrorType.NONE_AUTHORITY.getMessage());
        }

        findPosts.setContent(dto.getContent());
        findPosts.setTitle(dto.getTitle());
        postsRepository.save(findPosts);
        return PostsDto.from(postsRepository.findById(postId).get());
    }
}
