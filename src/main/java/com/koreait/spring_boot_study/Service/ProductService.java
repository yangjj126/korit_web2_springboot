package com.koreait.spring_boot_study.Service;

import com.koreait.spring_boot_study.dto.AddProductReqDto;
import com.koreait.spring_boot_study.entity.Product;
import com.koreait.spring_boot_study.exception.ProductinsertExeption;
import com.koreait.spring_boot_study.repository.ProductRepository;
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

}
