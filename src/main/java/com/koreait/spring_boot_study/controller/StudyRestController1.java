package com.koreait.spring_boot_study.controller;

import com.koreait.spring_boot_study.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// @Controller가 아닌, @RestController
@RestController
// HTML을 리턴하는 것이 아니다
// 데이터 (객체, 문자열...) 리턴
// rest컨트롤러만 쓸거임
@Slf4j // 어노테이션....
@RequestMapping("/study") //클래스 내부 컨트롤러의 공통 path url 지정 가능함
public class StudyRestController1 {
    // localhost:8080/study/test1
    @GetMapping("/test1")
    public String test1(){
        log.info("test1 컨트롤러 수신");
        return "양호 합니다";
    }

    // GET 요청의 경우, 쿼리스트링으로 데이터를 전달 할 수 있다
    // 클라이언트(브라우저) -> 서버로 전달
    // localhost:8080/study/test2?name="홍길동"&age=20
    // http://서버주소/경로?name="홍길동"&age=20
    @GetMapping("/test2")
    public String test2(@RequestParam("name") String str){
        // RequestParam은 쿼리스트링의 key와 매개변수이름이 같으면, 생략가능
        log.info("test2 컨트롤러 수신"); // 속도 빠름
        log.info("들어온 데이터 : {}" , str);
        return str;
    }

    // test1, test2모두 다 실행이 안됨 강사님꺼보고 수정하기

    // 파라미터 2개 ^& RequestParam 생략
    // localhost:8080/study/test3?name="홍길동"&age=20
    @GetMapping("/test3")
    public String test3(String name, Integer age){
        // RequestParam은 쿼리스트링의 key와 매개변수이름이 같으면, 생략가능
        // 숫자의 경우 알아서 매개변수 타입으로 변환(파싱)해준다..
        log.info("test3 컨트롤러 수신"); // 속도 빠름
        log.info("들어온 데이터 : {} , {}" , name, age);
        return "수신성공";
    }

    @GetMapping("/test4")
    // 객체리턴이 가능하다.. postman
    public List<String> test4(){
        log.info("test4 컨트롤러 수신");
        List<String> names = List.of("홍길동", "김길동", "고길동");
        return names;
    }

    // postman에서 작동을 안함

    // 객체리턴이 가능하다2
    // JSON - 서버와 클라이언트사이에 주고 받는
    // 웹데이터 표준형식 중 하나(XML등)
    // 자바의 Map과 유사하게 생겼음.
    // 자바의 객체도 전송이 가능하다. -> (자바객체 -> JSON -> javascript)
    @GetMapping("/test5")
    public List<Map<String, Object>> test5(){
        log.info("테스트5 컨트롤러 수신");
        List<Map<String, Object>> myData = new ArrayList<>();
        /*
        [
           {KEY, VALUE}
           {}
           {}
        ]
        ==> 이게 기본구조
         */
        Map<String, Object> data1 = Map.of(
                "name","홍길동",
                "age", 20
        );

        Map<String, Object> data2 = Map.of(
                "name","김길동",
                "age", 20
        );

        myData.add(data1);
        myData.add(data2);
        return myData;
    }

    // loclahost:8080/study/test6/{id}
    @GetMapping("/test6/{id}")
    public Map<String, Object> getStudent(@PathVariable("id") int id){ //PathVariable이 id를 받아주고, int로 id설정
        List<Student> studentList = List.of(
                new Student(1 , "피카츄"),
                new Student(2 , "라이츄"),
                new Student(3 , "김츄"),
                new Student(4 , "감츄")
        );
        // 1 ~ 4 범위에서 있는 번호인지 없다고 리턴,
        // 없으면 없다고 리턴
        // 우선 1. for문
        // 초기값은 null
        Student target = null;

        for (Student student : studentList){
            if (student.getId() == id){
                target = student;
            }
        } // 타깃 뽑아주기
        if (target == null){
            return Map.of("error", "해당학생은 존재하지 않습니다");
        }
        return Map.of("success", target);
    }


    //loclahost:8080/study/test7/?id=1&name=피카츄
    @GetMapping("/test7")
    public String test7(@ModelAttribute Student student){
        // 쿼리 스트링으로 데이터를 받을때, 객체로 받으면, 안될까? -> ModelAttribute
        // 1. 쿼리스트링의 key들과 클래스의 필드명이 동일해햐한다
        // 2. 생성자 혹은 setter 이 정의 해야한다.
        log.info("들어온 데이터 : { } " , student);
        return "성공";
    }
}
