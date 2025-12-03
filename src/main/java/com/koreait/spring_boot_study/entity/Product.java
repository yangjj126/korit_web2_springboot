package com.koreait.spring_boot_study.entity;

import lombok.*;

import java.util.List;

/*
entity -> 관계형 데이터베이스테이블과 일대일대응이 되는 자바 객체
관계형데이터 - 엑셀
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString // 이거 안해놓으면, 메모리 주소값이 보인다
@Builder
public class Product {
    private int id;
    private String name;
    private  int price;

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // 하나의 Product는 여러번 주문될 수 있다
    // -> 하나의 Product는 여러개의 orderDetail을 가지고 있다
    private List<OrderDetail> orderDetails;
    // 하나의 product_id로 여러 줄의 orderDetail 결과를 받을 수 있다
    // -> 하나의 Product 객체가 여러개의 orderDetail 객체를 가질 수 있다

}
