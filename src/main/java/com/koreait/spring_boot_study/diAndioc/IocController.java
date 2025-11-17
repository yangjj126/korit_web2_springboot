package com.koreait.spring_boot_study.diAndioc;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IocController {

//    @Autowired
    private IocService iocService;

    // 어플리케이션 컨텍스트 -> Ioc 컨테이너
    // Application에서 run하면, 가장 먼저 생성되는 싱글톤객체
    private ApplicationContext context;
    private ObjectMapper objectMapper; //외부라이브러리 bean

    // 생성자방식 주입하는 방법
    // 순환반복때문에 권장됨
    @Autowired
    public IocController(
            IocService iocService,
            ApplicationContext context
    ){
        this.iocService = iocService;
        this.context = context;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/ioc")
    public ResponseEntity<?> diTest() throws JsonProcessingException {
        int total = iocService.getTotal();
        double average = iocService.getAverage();
        Map<String, Object> resData = Map.of(
                "total" , total,
                "average", average
        );

        // 자바객체 -> JSON(문자열) BY Jackson 라이브러리(ObjectMapper)
        String jsonData = objectMapper.writeValueAsString(resData);

        // 외부에서 들어오거나 나가는 데이터는 문자열(string)
        // raw : 문자열로 보겠다, JSON : JSON포멧으로 보겠다
        return ResponseEntity
                .status(HttpStatus.OK) // 응답코드(헤더)
                .contentType(MediaType.APPLICATION_JSON) //  body의 자료구조지정(헤더)
                .body(jsonData); //
    }

    public ResponseEntity<?> showBeans() {
        String[] beans = context.getBeanDefinitionNames();
        return ResponseEntity.ok(beans);
    }

    //  우리가 직접 선언하지 않은 클래스의 객체는 bean으로 만들수 없는가
    //  -> @Configuration
}
