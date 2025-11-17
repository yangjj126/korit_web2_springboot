package com.koreait.spring_boot_study.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Ioc컨테이너에 직접 등록할 Bean들을 메서드 형식으로 선언하면 됩니다
public class AppConfig {
    // 컴포넌트 스캔 : => @Component, @Service, @Repo
    // 그런데, 외부라이브러리는 스캔범위 밖이다.


    // Jackson 라이브러리의 ObjectMapper 클래스를
    // bean 으로 만들어서, Ioc 컨테이너에 보관,
    // bean 으로 등록할 클래스는 반드시 상태가 없어야 한다
    @Bean // bean 등록
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
