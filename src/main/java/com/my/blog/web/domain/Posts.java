package com.my.blog.web.domain;

import com.my.blog.web.dto.ReplyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    private Posts(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public static Posts of(String title, String content)
    {
        return new Posts(title, content);
    }
}
