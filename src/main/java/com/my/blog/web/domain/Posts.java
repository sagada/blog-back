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
    private String content;
    private String imgUrl;
    @OneToMany(mappedBy = "posts")
    private List<Reply> replyList = new ArrayList<>();

    private Posts(String title, String content)
    {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
    }

    public static Posts of(String title, String content)
    {
        return new Posts(title, content);
    }
}
