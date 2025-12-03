package com.koreait.spring_boot_study.repository.mapper;

import com.koreait.spring_boot_study.entity.Product;
import com.koreait.spring_boot_study.model.Top3SellingProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    3. jdbc 에서 사용하던 rsToProduct()메서드 -> 자동으로 지원하겠다
    객체간 참조(그래프 탐색)을 지원하겠다.
    db의 테이블과 1:1대응되는것이 entity -> fk컬럼을 id필드로 가지고 있음
    객체 지향적(그래프 탐색) entity -> fk컬럼을 객체 자체를 필드로 가지고 있음(연관관계 설정)

    ---- mybatis 내부구현에 대한 간략한 이해 ----
    mapper(interface : 추상체) --- dynamicProxy(mybatis가 알아서) --- xml(실제 구현체)
    서비스는 mapper interface만 알고 있음, 주입받고 있다
    실제로 ioc컨테이너에서 주입해주는것은 mapper 인터페이스가 아니라, dynamicProxi객체
    dynamicProxy객체를 mtbatis가 xml보고, 생성 및, bean등록을 한다
     */

    // 1. 다건조회(전체조회)
    List<Product> findAllProducts();
    // 2. 단건조회(상품 하나만 조회)
    String findProductNameById(int id);
    // 상품 추가
    int insertProduct(
            @Param("name") String name,
            @Param("price") int price
    );
    // @Param -> xml 에서 매개변수 이름을 전달할때, 사용
    // 매개변수들을 HashMap형태로 가져가게됨
    // 우리가 @Param에 적어주는 것은 Key값
    // xml에서 해당key값을 적어줘서 value를 동적으로 처리
    // Param을 적어주지 않으면, 컴파일러 옵션에 따라서 작동할때도 잇고 안하기도함
    // -> 매개변수가 2개 이상일 경우, 적어주기

    // 단건 삭제
    int deleteProductById(int id);
    // 단건 업데이트
    int updateProduct(
            @Param("id") int id ,
            @Param("name") String name,
            @Param("price") int price
    );

    // join 결과를 받아옴
    // "판매량 기준 top3" 를 받아오자!
    public List<Top3SellingProduct> findTop3SellingProducts();

    // productId로 판매량까지 같이 조회
    Product findProductWithQuantities(int productId);

    // 상품이름, 최소 가격, 최대 가격 필터링 검색
    // where product_name like '% + {Product} + %'
    List<Product> searchDetailProducts(
            @Param("nameKeyword") String nameKeyword,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice
    );

    int insertProducts(List<Product> products);
}
