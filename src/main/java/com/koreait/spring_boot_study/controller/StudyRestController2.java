package com.koreait.spring_boot_study.controller;

import com.koreait.spring_boot_study.entity.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.Arrays.stream;

@RestController
@Slf4j // alt + shift + 방향키
@RequestMapping("/practice")
// 스프링 부트는 톰캣(자바언어로 만든 서버)를 내장
// 로컬에서 8080포트에서 실행된다.
// localhost:8080 -> 스프링부트 주소
// localhost:8080/practice/add
// localhost:8080/practice/add?num1=10&num2=20
 public class StudyRestController2 {

    // 실습) 컨트롤러 2개를 만들기
    // 1. 쿼리스트링으로 숫자2개를 받아서 더한 결과를 응답하는 컨트롤러
    @GetMapping("/add")
    public int add(int num1, int num2) {
        return num1 + num2;
    };

    // 2. 쿼리스트링으로 숫자4개를 받아서 평균을 응답하는 컨트롤러
    // localhost:8080/practice/average?num1=10&num2=20&num3=30&num4=40
    @GetMapping("/average")
    public double average(int num1,int num2,int num3,int num4){
        // 자바복습) double 크기가 int 보다 크니까
        // 서로 연산하면, 자동으로 double로 변환
        return (num1 + num2 + num3 + num4)/4.0;
    }

//    @GetMapping("/add")
//    public int add(int num1, int num2){
//        int result = num1 + num2;
//        log.info("완료");
//        return result;
//    }
//
//    @GetMapping("/average")
//    public double average(int num1,int num2,int num3,int num4){
//        double average = (double) (num1 + num2 + num3 + num4)/4;
//        return average;
//    }



    /*
    포스트 응답으로
    [
        {
            "name" : "홍길동"
            "address : "부산시 연제구"
        },
        {
            "name" : "고길동"
            "address" : "부산시 부산진구"
        },
    ]
     */


    @GetMapping("/profiles")
    public List<?> getProfiles() {
        List<Map<String, String>> myData = new ArrayList<>();
        Map<String, String> data1 = Map.of(
                "name" , "홍길동"
                ,"address", "부산시 연제구"
        );
        Map<String, String> data2 = Map.of(
                "name" , "고길동"
                ,"address", "부산시 부산진구"
        );
        myData.add(data1);
        myData.add(data2);
        return  myData;
    }



    //원시 타입은 Object가 아니다
    // int는 Object가 아니다
    @GetMapping("/profiles2")
    public List<?> getProfiles2(){
        return List.of(
                Map.of(
                        "name", "홍길동"
                        ,"address", "부산시 연제구"
                ),
                Map.of(
                        "name", "고길동"
                        ,"address", "부산 지구"
                )
        );
    }



    // 게시물 조회 컨트롤러를 완성해주세요
    @GetMapping("/post/{id}")
    public Map<String, Object> getPost(@PathVariable("id") int id){
        List<Post> postList = List.of(
                new Post(1,"페이커 그는 신인가","ㅇㅈ"),
                new Post(2,"구마 그는 신인가","ㅇㅈ"),
                new Post(3,"케리아 그는 신인가","ㅇㅈ"),
                new Post(4,"오너 그는 신인가","ㅇㅈ")
        );

        // stream 을 활용
        // Optional -> null 일수도 있는 값을 포장한것
        Optional<Post> optionalPost= postList.stream()
                .filter(post -> post.getId() == id)
                .findFirst(); // 처음찾은 것을 가져오세요. 타입이 obtional

        if (optionalPost.isEmpty()){ // optional이 가지고 있는 값이 null이라면,
            return Map.of("error", " 해당id의 게시글은 존재안함 ");
        }

        Post target = optionalPost.get(); // 옵셔널에 포장된 실제값 꺼내기
        return Map.of("success",target);
    }
}
