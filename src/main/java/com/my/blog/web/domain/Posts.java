package com.my.blog.web.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public Posts(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private Posts(String title, String content, UserEntity userEntity)
    {
        this.title = title;
        this.content = content;
        this.userEntity = userEntity;
    }

    public static Posts of(String title, String content, UserEntity userEntity)
    {
        return new Posts(title, content, userEntity);
    }
}
