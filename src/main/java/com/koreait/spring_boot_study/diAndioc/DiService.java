package com.koreait.spring_boot_study.diAndioc;

import java.util.List;

public class DiService {
    private static DiService instance;
    // 싱글톤 적용
    private DiRepository diRepository;

    // 생성자 호출 쪽에서 DiRepo - 를 주입
    // 위의 diRepository를 받아온다
    private DiService(DiRepository diRepository){
        this.diRepository = diRepository;
    }


    // 외부에서, getInstance를 호출 쪽에서,DiRepo를 주입한다
    public static DiService getInstance(DiRepository diRepository) {
        if ( instance == null) {
            instance = new DiService(diRepository);
        }
        return instance;
    }

    // 위에 까지가 싱글톤 패턴




    // 총점 구하는 메서드
    public int getTotal(){
        List<Integer> scores = diRepository.getScores();
        int total = 0;
        for (Integer score : scores){
            total += score;
        }
        return total;
    }

    // 평균을 구하는 서비스
    public double getAverage(){
        List<Integer> scores = diRepository.getScores();
        double avg = (double) getTotal() / scores.size();
        return avg;
    }



}
