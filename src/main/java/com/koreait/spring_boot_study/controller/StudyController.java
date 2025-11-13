package com.koreait.spring_boot_study.controller;

// Controller - 통신을 할 때, 클라이언트(브라우저)와 서버(스필부트) 사이의 데이터를 주고 받는 진입점
// 두가지 유형 (스프링에서)
// 걍controller , RestController

// 1. 걍 컨트롤러 - html(웹페니지)파일을 반환하는 컨트롤러 - 서버사이드 렌더링(SSR)
// 2. Rest Controller - JSON, 문자열등릐 데이터들만 반환하는 컨틀로러 - 클라이언트사이드 렌더링(CSR)

import com.koreait.spring_boot_study.model.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


// 스프링은 ANNOTATION을 기반으로 많이 작동한다.. 무슨 말일까
@Controller
public class StudyController {
    // 자바와 다르게 이해하려하지 말고,
    // 이렇게 했더니 이렇게 되었다는 식으로 이해

    // 인터넷 통신(HTTP 통신) - 웹에서 브라우저(클라이언트)와 서버가 데이터를 주고 받는 규칙
    // 1. 한번 요청하면, 한 번 응답한다
    // 요청/응답 - 자바 객체(header, body로 이루어져 있다)
    // 2. 요청의 경우, 메서드(방법)
    // - GET 요청 : 자원조회 - body가 없다, url에 쿼리스트링으로 데이터 전달

    // 클라이언트에서 서버로 요청을 보냄 ( 네이버 서버로 GET요청보냄 f5 )
    // - POST 요청 : 자원 생성 요청
    // - DELETE 요청 : 자원 삭제 요청
    // - PUT 요청 : 자원전체 수정 요청
    // - PATCH 요청 : 자원 일부 수정 요청
    // 톰캣서버(8080포트) + 로컬 -> localhost:8080(서버주소)
    // localhost:8080/hello -> 접속(GET요청)하면, helloPage 컨트롤러가 실행됨.
    @GetMapping("/hello") // == hello 라는 경로로 ---////--- get 요청이 들어오면, 실행하세요
    public String HelloPage(Model model) {
        System.out.println("hello 컨틀롤러 수신");
        Hello hello = Hello.builder()
                .hello1("데이터1")
                .hello2("데이터2")
                .build();

        // hello 라는 객체를 "hello"이름으로 html에 전달
        model.addAttribute("hello", hello);
        // templates 경로 안에, hello.html을 찾아서 클라이언트에 보내줘라.
        return "hello";
    }
}
// 스프링부트는 서버를 구성하는 코드들

