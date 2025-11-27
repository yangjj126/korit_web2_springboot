package com.koreait.spring_boot_study.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Post { // 테이블명 : post -> 클래스명 : Post
    private int id; // 컬럼명 : post_id -> 자바 : PostId
    private String title; // 칼럼명 : post_title -> 자바 : postTtile
    private String content; // 칼럼명 : post_content -> 자바 : postContent
}

