package com.koreait.spring_boot_study.diAndioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
Component역할을 하는 어노테이션들
1. @Component -> 특별한 역할 x
2. @RestController -> Http 요청 / 응답 처리
3.
4. 나머지 나중에 적기
 */



@Service
public class IocService {

    /*
    스프링부트에서는,
    객체생성(NEW) / 의존성주입(di)의 주도권이 프레임워크( springboot )에 있다
    언제 생성되는지, 소멸되는지를 스부가 결정한다 -> Inversion Of Control(Ioc)

    스프링부트는 Ioc컨테이너가 존재한다
    이 컨테이너의 어노테이션(@Component)가 붙은 클래스들을 싱글톤객체로 생성해서 호출한다

    1. SpringApplication.run() 실행시, Ioc컨테이너 객체를 생성한다 (싱글톤)
    2. 컴포넌트스캔(@Component 붙은 클래스 탐색 )
    // 탐색범위 : run()한곳이 포함된 패키지를 기준으로 하위 전체
    3. 스캔이 끝나면, 그 클래스들로 부터 싱글톤객체를 생성한다... => 요거를 Bean - 시점(1)
    4. 생성한 Bean들을 Ioc 컨테이너에 보관
    5. 필요한곳에 해당 Bean을 주입한다 -- 시점(2)
     */
    @Autowired // 시점(2)에서 주입함.
    private IocRepository iocRepository;

    @Autowired // 시점(1)에서 주입한다 - 생성자 주입 권장
    public IocService(IocRepository iocRepository){ //IOC레포를 필요로 함
        this.iocRepository = iocRepository;
    }

    /*
    순환참조 (A , B라는 bean을 예시로)
    A는 B를 필드로
    B는 A를 필드로
    A를 만들려고 하니 B가 필요하고 -> B만들자
    B를 만들려고 하니 A가 필요 -> A만들자 .. 무한 반복
    그래서
    시점1을 추천하는 이유는 미리 탐지 가능 하고, 예방가능하다
     */

    // 총점 구하는 메서드
    public int getTotal(){
        List<Integer> scores = iocRepository.getScores();
        int total = 0;
        for (Integer score : scores){
            total += score;
        }
        return total;
    }

    // 평균을 구하는 서비스
    public double getAverage(){
        List<Integer> scores = iocRepository.getScores();
        double avg = (double) getTotal() / scores.size();
        return avg;
    }



}
