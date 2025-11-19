package com.koreait.spring_boot_study.controller.advice;

import com.koreait.spring_boot_study.exception.PostNotFoundException;
import com.koreait.spring_boot_study.exception.ProductinsertExeption;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 예외는 catch되지 않으면, 계속 전파된다
// 계속해서 호출한쪽으로 돌아간다
// 컨트롤러까지 전파되었지만, catch가 없없음 -> dispather servlet(접수원)까지 올라감

//  <dispather servlet(접수원)>
// 1. @RestControllerAdvice 어노테이션을 가진 클래스를 찾음 ( -> 핸들러를 찾음 )
// 2. 전파되어온 예외의 클래스를 처리할 수 있는 컨트롤러를 찾는다
// 3. 찾으면, 해당컨트롤러를 실행


// 게시글을 찾을 수없음(404)
// 조회불가(404)
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> handlePostNotFound(
        PostNotFoundException e
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    public ResponseEntity<?> handlerProductError(
            ProductinsertExeption e
    ){
        // 권한이 없다 -> 403
        // 필드가 누락 -> 400
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
