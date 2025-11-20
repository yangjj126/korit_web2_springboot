package com.koreait.spring_boot_study.controller.advice;

import com.koreait.spring_boot_study.exception.PostInsertException;
import com.koreait.spring_boot_study.exception.PostNotFoundException;
import com.koreait.spring_boot_study.exception.ProductNotFoundException;
import com.koreait.spring_boot_study.exception.ProductinsertExeption;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 예외는 catch 되지 않으면, 계속 전파된다
// 계속해서 호출한쪽으로 돌아간다
// 컨트롤러까지 전파되었지만, catch가 없없음 -> dispather servlet(접수원)까지 올라감

//  < dispather servlet(접수원) >
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

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFound(
            ProductNotFoundException e
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }





    @ExceptionHandler(ProductinsertExeption.class)
    public ResponseEntity<?> handlerProductError(
            ProductinsertExeption e
    ){
        // 권한이 없다 -> 403
        // 필드가 누락 -> 400
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(PostInsertException.class)
    public ResponseEntity<?> handlerPostError(
            PostInsertException e
    ){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }


    // POST 부분 작성하기











    // validation 예외처리 핸들러(추가, 수정) - 400
    // Validation에 실패하면 MethodArgumentNotValidException을 던지게 됨
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> Validation(
            MethodArgumentNotValidException e

    ){
        //  해당 Dto에 Validation 어노테이션이 붙은 필드를 모두 검사
        //  중요) 여러개중 한가지 필드만 에러에 추가하는 것이 아닝
        //  ErrorMap을 리턴할건데, 필드가 여러개니깐, Map이 여러개
        //  리턴값이 Map이 여러개 들어간 리스트리턴
        List<Map<String, String>> errorResp = null;

        // dto로 변환될때 담는 에러메시지 모두 담은 객체
        BindingResult bindingResult = e.getBindingResult();

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors() // 필드에러들을 리스트로 변환하는 함수
                    .stream() // [객체1, 객체2....]
                    .map(fieldError -> Map.of(
                            fieldError.getField(), fieldError.getDefaultMessage()
                    )) // [Map1, Map2...] -> List<Map<String, String>>
                    .collect(Collectors.toList());
        }
        /*n
         [
         {
          "name" : "이름은 비울수없습니다"
         }
         ,{
         "price" : "이름은 비울수없습니다"
         }
         ]
         */
         return ResponseEntity
                 .status(HttpStatus.BAD_REQUEST)
                 .body(errorResp);
    }
}
