package com.my.blog.web.dto.messge;

import lombok.Getter;

@Getter
public enum ErrorType {
    NONE_USER("없는 유저입니다."),
    NONE_POST("없는 포스트 ID입니다."),
    NONE_AUTHORITY("권한이 없는 유저입니다.");
    private String message;


    ErrorType(String message)
    {
        this.message = message;
    }
}
