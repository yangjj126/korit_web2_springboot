package com.koreait.spring_boot_study.controller;

import com.koreait.spring_boot_study.Service.ProductService;
import com.koreait.spring_boot_study.dto.AddProductReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    // 전체 상품명 조회
    @GetMapping("/name/all")
    public ResponseEntity<?> getProductNames(){
        return ResponseEntity.ok(productService.getAllProductNames());
    }

    // 상품한개 조회
    // localhost:8080/product/name/{id}
    @GetMapping("/name/{id}")
    // 중괄호는 하는게 아니다
    public ResponseEntity<?> getProductName(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductNameById(id));
    }
    // 왜 오류 뜨는지 확인해보자



    // 컨트롤러에서 추가한다
    // DB에 추가 -> POST
    @PostMapping("/add")
    public ResponseEntity<?> postProduct(
            @RequestBody AddProductReqDto dto
    ){
        productService.addProduct(dto); // 이것부터 컨트롤러에서 쫙 내려가게되는 구조
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("성공");
    }

}

