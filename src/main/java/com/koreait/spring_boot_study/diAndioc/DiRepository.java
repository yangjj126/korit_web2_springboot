package com.koreait.spring_boot_study.diAndioc;

import java.util.List;

public class DiRepository {
    // 싱글톤 패턴을 적용시키기 -> 객체 하나만 생성해서, 돌려 쓰자
    // 1. 생성자 접근을 private선언으로 막는다
    // 2. 자기 자신의 타입을 static 필드로 가진다
    // 3. 외부접근이 가능한 static메서드로 단 하나의 객체만 사용하도록 설계

    // DB대용
    private List<Integer> scores = List.of(100,90,80,70);


    // 자기 자신의 타입을 static 필드로 가진다
    private static DiRepository instance;
    // DiRepository.instance  (x)

    private DiRepository(){

    }

    public static DiRepository getInstance(){
        if (instance == null){ //최초는 null
            instance = new DiRepository();
        }
        return instance;
    }

    public List<Integer> getScores() {
        return scores;
    }






}
