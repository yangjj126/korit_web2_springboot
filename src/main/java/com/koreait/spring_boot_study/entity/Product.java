package com.koreait.spring_boot_study.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
entity -> 관계형 데이터베이스테이블과 일대일대응이 되는 자바 객체
관계형데이터 - 엑셀
 */
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private  int price;
}
