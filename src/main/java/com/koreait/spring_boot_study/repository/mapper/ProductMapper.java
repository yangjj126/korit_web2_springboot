package com.koreait.spring_boot_study.repository.mapper;

import com.koreait.spring_boot_study.entity.Product;
import com.koreait.spring_boot_study.model.Top3SellingProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
// xml파일과 1:1매칭되는 자바 파일
// xml을 통해 db에서 가져온 결과(rs)를 자바객체로 가져오는 역할

@Mapper
public interface ProductMapper {
    /*
    JDBC의 문제점
    conn, ps, rs try-catch-finally
    -> 이런코그글이 통째로 보일러 코드이다
    -> 자동완성되면 좋겠다
    개발자는 SQL만 신경쓰고 싶다

    2. SQL은 String 자료형으로 작성했엇음
    -> 자바랑은 sql은 독립적이지만, 왜 java코드로 작성해야 하는가
    -> sql이 길어지면, java코드가 어지렵혀진다. (java와 분리)
    : java파일 말고, xml로 따로 분리시키겠다

    3. jdbc에서 사용하던 rsToProduct()메서드 -> 자동으로 지원하겠다
    객체간 참조(그래프 탐색)을 지원하겠다.
    db의 테이블과 1:1대응되는것이 entity -> fk컬럼을 id필드로 가지고 있음
    객체 지향적(그래프 탐색) entity -> fk컬럼을 객체 자체를 필드로 가지고 있음(연관관계 설정)
     */



    // 1. 다건조회(전체조회)
    List<Product> findAllProducts();
    // 2. 단건조회(상품 하나만 조회)
    String findProductNameById(int id);
    // 상품 추가
    int insertProduct(String name, int price);
    // 단건 삭제
    int deleteProductById(int id);
    // 단건 업데이트
    int updateProduct(int id, String name, int price);

    // join 결과를 받아옴
    // 판매량 기준 top3를 받아오자!
    public List<Top3SellingProduct> findTop3SellingProducts();
}
