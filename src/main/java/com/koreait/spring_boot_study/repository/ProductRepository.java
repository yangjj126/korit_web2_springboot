package com.koreait.spring_boot_study.repository;


import com.koreait.spring_boot_study.entity.Product;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    // DB 대용
    private List<Product> products = new ArrayList<>(
            Arrays.asList(
                    new Product(1, "노트북", 1500000),
                    new Product(2, "마우스", 30000),
                    new Product(3, "키보드", 80000),
                    new Product(4, "모니터", 350000)
            )
    );
    // 컨트롤러 -> 서비스 -> 레포지토리
    // 1. 전체조회

    // 리스트자체를 다 넘겨준다
    public List<Product> finalAllProducts(){
        return products;
    }

    // 2. 상품하난만 조회
    // id로 상품이름 조회
    public String findProductNameById(int id){
        // Optional : 컨테이너 클래스 null일수도 있고, 아닐 수도 있다
        Optional<Product> optionalProduct = products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();

        // 옵셔널을 펼치는것 -> Repository에서 할까? Service에서 할까?
        // 매칭되는 첫번쩨 객체를 리턴 아니면,
        // null을 옵셔널에 감싸서 리턴
        if (optionalProduct.isEmpty()){
            // 감싼것이 null 인경우에는 isEmpty()가 된다
            // 예외를 던져야 한다
            return "해당id상품 없음";
        }

        String targetName = optionalProduct.get().getName();

        return targetName;

    }

}
