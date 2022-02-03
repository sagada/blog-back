package com.my.blog.web.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private UserEntity(String username, String email, String password)
    {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static UserEntity of(String username, String email, String password)
    {
        return new UserEntity(username, email, password);
    }

    public void invalidUserEntity()
    {
        if (this.email == null)
        {
            throw new RuntimeException("Invalid UserEntity");
        }
    }

}
