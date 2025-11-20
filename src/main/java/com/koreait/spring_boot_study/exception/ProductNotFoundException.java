package com.koreait.spring_boot_study.exception;
// 클래스이름이 중요하다

// 커스텀예뢰 - RuntimeException을 상속받으면, 커스텀예외를 만들 수 있다
// 게시글을 찾을 수 없을때(id 조회, 이름조회 ..)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

}
