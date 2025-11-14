package com.koreait.spring_boot_study.controller;

import com.koreait.spring_boot_study.dto.AddPostRequestDto;
import com.koreait.spring_boot_study.dto.StudyRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/study3")
@Slf4j
public class StudyController3 {
    /*
    HTTP 요청 메서드 (GET, POST, PUT, PATCH, DELETE)

    GET 요청을 제외한 모든 요청메서드에는 body가 존재한다

    body의 특징 : url과 무관하게, 데이터를 송신가능
    JSON을 body에 담아서 송신
     */



    //localhost:8080/study3/test1
    /*
    {
        "data1" : "데이터1"
        "data2" : "데이터2"
    }
     */


    @PostMapping("/test1")
    public String test1(@RequestBody Map<String, Object> data) {
        // @RequestBody -> body에 작성되어있는 json데이터를 알아서 자바데이터를 받아준다
        // 참고) Jackson 라이브러리가 개입해서 자동으로 변환해준다
        log.info("test1 컨트롤러 수신완료");
        log.info("들어온 데이터 : { }", data);

        return "성공";
        // 오류디버깅 해보기
    }


    @PostMapping("/test2")
    public String test2(@RequestBody StudyRequestDto dto) {
        // @RequestBody -> body에 작성되어있는 json 데이터를 알아서 자바데이터를 받아준다
        // 참고) Jackson 라이브러리가 개입해서 자동으로 변환해준다
        log.info("test2 컨트롤러 수신완료");
        log.info("들어온 데이터 : {}", dto);

        return "성공";
        // 오류디버깅 해보기
    }

    // 실습) test3라는 이름으로 AddPostReqDto 타입의 데이터를 수신해주세요.

    @PostMapping("/test3")
    public ResponseEntity<?> test3(@RequestBody AddPostRequestDto dto) {
        // ReesponseEntity : HTTP 응답을 자바에서 커스터마이징하기 편하게 만들어준다
        // 제너릭타입을 받는다 -> body에 들어가는 데이터 타입
        // HTTP 상태 코드, BODY, header 등을 쉽게 지정할 수 있다

        /*
        {
        "title" : "제목1"
        "content" : "내용2"
        }
         */


        log.info("test3 컨트롤러 수신완료");
        log.info("들어온 데이터 : {}", dto);

        /*
        HTTP 상태 코드
        200 -> 성공
        400 -> 요청을 잘못했다
        500 -> 서버가 잘못했다
        200 -> OK , 201 : 생성성공 CREATED
        400 : 잘못된 요청 BAD_REQUEST, 401인증이 실패했습니다 UNAUTHORIZED
        403 : 권한이 없다 FORBIDDEN, 404 : 리소스가 없다 , 주소입력이 잘못되었다 NOTFOUND
        500 : 서버내부오류 (코드문제이거나, 예외처리 불량, DB 에러가 자바서버까지 올라오는 경우 )
         */
        return ResponseEntity.status(HttpStatus.OK).body("성공");

    }


}
