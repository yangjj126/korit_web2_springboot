package com.koreait.spring_boot_study.diAndioc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class Dicontroller {
    // Controller : 오로지 요청, 수신, / 응답송신관련 코드 작성
    // -> Service : 비즈니스 로직 / 트렌젝션 관리
    // -> Repository : DB연결 / DB와 연관된 코드 작성



    @GetMapping("/di")
    public ResponseEntity<?> diTest() {

        // 못적은 부분 다 적기

        // 이 의존성을 내가 직접 코드로 컨트롤 하고 있다 -> DI를 직접하고 있다

        // getInstance()를 직접 호출 하면서, 객체생성도 직접 컨트롤 하고 있다

        DiRepository diRepository = DiRepository.getInstance();
        DiService diService = DiService.getInstance(diRepository);

        int totalScore = diService.getTotal();
        double averageScore = diService.getAverage();

        Map<String, Object> resMap = Map.of(
                "총점", totalScore
                , "평균", averageScore
        );

        return ResponseEntity.ok(resMap);
    }


}
