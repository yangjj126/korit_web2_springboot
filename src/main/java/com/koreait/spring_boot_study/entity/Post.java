package com.koreait.spring_boot_study.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter @Setter
public class Post { // 테이블명 : post -> 클래스명 : Post
    private int id; // 컬럼명 : post_id -> 자바 : PostId
    private String title; // 칼럼명 : post_title -> 자바 : postTtile
    private String content; // 칼럼명 : post_content -> 자바 : postContent

    public Post(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    /*
        pk를 fk로 들고 있는 쪽이 N이다
        Post : Comment = 1 : N
    */
    private List<Comment> comments;
}

