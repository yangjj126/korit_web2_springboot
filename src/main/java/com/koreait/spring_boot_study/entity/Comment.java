package com.koreait.spring_boot_study.entity;

public class Comment {
    private int commentId;
    private String content;

    // private int PostId;
    private Post post; // fk 대신에 객체를 필드로 가져야한다
    // comment.getPost().getId() -> 계속된 참조로 탐색하는 것( 객체 그래프 탐색 )

    // 하나의 글에는 여러개의 댓글이 있다
    // 한개의 Post는 여러개의 Comment를 가질 수 있다
    // 1. fk 누가 가지고 있어야 하나? Comment가 PostId(fk)가지고 있어야 한다
    // 2. 하나의 댓글은 한개의 글에만, 달릴 수 있다
    // -> Post : Comment = 1:N
}
