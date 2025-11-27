package com.koreait.spring_boot_study.repository.impl;


import com.koreait.spring_boot_study.entity.Product;
import com.koreait.spring_boot_study.model.Top3SellingProduct;
import com.koreait.spring_boot_study.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ProductRepository implements ProductRepo {
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
    public List<Product> findAllProducts(){
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




    // 상품 추가
    public int insertProduct(String name, int price){
        // 아이디 최대값 추적
        int maxId = products.stream()
                .map(product -> product.getId())
                .max((id1, id2) -> id1 - id2)
                .get();
                // 저번에 배운거 복습해야겠지 ,,, 함수형메서드 배운것들말이여

        // for문 사용
        int maxId2 = 0;
        for (Product product : products){ // product 하나씩 꺼내오고,
            if (product.getId() > maxId){ // 비교
                maxId2 = product.getId(); // 꺼내온값이 크면, 그것으로 업데이트
            }
        }

        Product product = new Product(maxId + 1,  name, price);
        products.add(product);
        return 1; //
    }






    //  @@ 단건 삭제
    // id를 통해서 단건을 삭제하는 메서드
    public int deleteProductById(int id){
        // 매개변수로 들어온 id가 유효한지?
        // 유효하지 않으면, -> 0을 린턴 (service)에서
        Optional<Product> target = products.stream()
                .filter(product -> product.getId() == id)
                .findFirst(); // 매핑되는 첫번째 데이터를 Optional에 포장해서 들고 오세요

        if (target.isEmpty()){ // 찾은 optional를 언패킹했더니 null이라면,
            return 0;
        }

        // 코드가 진행이 된다는 것은 -> if 문에 걸리지 않은 것
        // 그러면, 이제 제거를 해주면 된다
        // 옵셔널 언패킹
        Product product = target.get();
        // 제거
        products.remove(product);
        // repo 에서는 products에서 삭제하는 기능
        log.info("상품삭제완료 : {}", product);
        return 1;
    }

    // @@ 단건 업데이트
    // 기존의 id를 받아서, 그 내용을 변화시키고 싶다는 것
    public int updateProduct(int id, String name, int price){
        // 매개변수로 들어온 id가 유효한 id 인지 확인해야 한다
        Product target = null; //null로 초기화

        // 우선, id 부터 확인
        for (Product product : products){
            if (product.getId() == id){ //매개변수로 들어온 id와 같다면
                target = product;
                break;
            }
        }

        if (target == null){ // target이 업데이트가 안되었다면,
            // id는 유효하지 않은것
            return 0; // 업데이트는 0건 했엉
        } // 여기에 안걸렸으면, target은 업데이트되있음


        // 아래 부분 이해 안됨

        // List 업데이트
        // List.set(index, 저장할 데이터)  이거 코테시간에 배웠다
        // 리스트.indexOf(데이터) -> 해당데이터릐 index 번호를 리턴
        int index = products.indexOf(target);
        // entity 형태로 데이터베이스에 저장됨

        //******************************************************
        Product newProduct = new Product(id, name, price);
        // 위에 매개변수에 있는 id, name, price를 받아와서, 새롭게 형성된 객체를 만든다

        // target이 있던 자리에 newProduct가 저장됨
        products.set(index, newProduct);
        // 그 target의 index 안에 newProduct 객체를 다 집어넣겠다...


        return 1;
    }

    @Override // 구현 x
    public List<Top3SellingProduct> findTop3SellingProducts() {
        return List.of();
    }
}
