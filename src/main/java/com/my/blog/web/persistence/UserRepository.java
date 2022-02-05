package com.my.blog.web.persistence;

import com.my.blog.web.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
    UserEntity findByUsername(String userName);
    UserEntity findByEmailAndPassword(String email, String password);
}
