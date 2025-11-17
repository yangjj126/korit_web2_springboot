package com.koreait.spring_boot_study.diAndioc;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IocRepository {

    // DB대용
    private List<Integer> scores = List.of(100,90,80,70);


    public List<Integer> getScores() {
        return scores;
    }






}
