package com.my.blog.web.service;

import com.my.blog.web.domain.UserEntity;
import com.my.blog.web.dto.messge.ErrorType;
import com.my.blog.web.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity)
    {
        userEntity.invalidUserEntity();

        final String email = userEntity.getEmail();

        if (userRepository.existsByEmail(email))
        {
            log.warn("이미 존재하는 이메일");
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String email, final String password)
    {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public UserEntity findById(Long userId)
    {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException(ErrorType.NONE_USER.getMessage()));
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException(ErrorType.NONE_USER.getMessage()));
    }
}
