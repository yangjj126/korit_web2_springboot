package com.koreait.spring_boot_study.model;

/*
- DB에 있는 1:1테이블과 매핑된 엔티티가 아니다
- 기술적인 부분과 상관이 없음 -> TOP3를 뽑아내리는 요구사항은 업계의 요구 ( 비즈니스 로직 )
- 클라이언트가 요구한 것(top3 판매량 순위 조회)
: 아런것들을 domain model 이라고 한다.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Top3SellingProduct {
    private int productId; // product_id
    private String productName; // product_name
    private int totalSoldCount; // total_sold_count(집계함수 결과)
}

