package com.koreait.spring_boot_study.Service;

import com.koreait.spring_boot_study.dto.AddProductReqDto;
import com.koreait.spring_boot_study.dto.ModifyProductReqDto;
import com.koreait.spring_boot_study.entity.Product;
import com.koreait.spring_boot_study.exception.ProductNotFoundException;
import com.koreait.spring_boot_study.exception.ProductinsertExeption;
import com.koreait.spring_boot_study.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    //    private ProductRepository productRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    // 생성자 조져주고,


    // 1. 전체조회(이름만)
    public List<String> getAllProductNames() {
        List<String> productNames = productRepository.finalAllProducts()
                .stream().map(product -> product.getName())
                .collect(Collectors.toList());
        return productNames;
    }


//    List<String> productNames2 = new ArrayList<>();
//    List<Product> products = productRepository.finalAllProducts();
//    for (Product product : products) {
//        productNames2.add(product.getName());
//    }
//      return productNames;

    // 2. 하나만 조회(상품 이름)
    public String getProductNameById(int id){
        String targetName = productRepository.findProductNameById(id);
        // 나머지 보완하기 id 들어가는게 맞는지 확인
        return targetName;
    }


    // 3. 상품추가
    public void addProduct(AddProductReqDto dto){
        int successCount = productRepository
                .insertProduct(dto.getName() , dto.getPrice());

        if (successCount <= 0){
            // 예외 - 나중에
            throw new ProductinsertExeption("상품등록 불가");
        }
    }

    // 어떻게 했냐
    // AddProductReqDto dto를 받아서,
    /*
    <Controller>
    컨트롤러에 이미 제이슨으로 값이 입력되어있고, 컨트롤러에서 .addProduct를 조지고
    <Service>
    addProduct안에서
    InsertProduct를 통해서, dto.getName() , dto.getPrice()을 하고,
    <Repostitory>
    InsertProduct 실행
     */


    // 4. 상품삭제
    public void removeProduct(int id){
        int successCount = productRepository.deleteProductById(id);
        if (successCount <= 0){
            // ProductNotFound 예외를 사용
            // exception 만든다
            throw new ProductNotFoundException("해당 상품은 존재하지 않는다");
        }
    }

    // 5. 상품 업데이트
    public void modifyProduct(int id, @Valid ModifyProductReqDto dto){
        int successCount = productRepository.updateProduct(id, dto.getName(), dto.getPrice());

        if (successCount <= 0){
            throw new ProductNotFoundException("해당 상품은 존재하지 않습니다");
        }
    }


}
