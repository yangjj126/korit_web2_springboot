package com.koreait.spring_boot_study.controller;

import com.koreait.spring_boot_study.Service.ProductService;
import com.koreait.spring_boot_study.dto.AddProductReqDto;
import com.koreait.spring_boot_study.dto.ModifyProductReqDto;
import jakarta.validation.Valid;
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
           @Valid @RequestBody AddProductReqDto dto
    ){
        productService.addProduct(dto); // 이것부터 컨트롤러에서 쫙 내려가게되는 구조
        // dto에 이미 데이터들이 JSON 으로 입력되어있다
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body("성공");
    }


    // localhost:8080/product/1 - Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        productService.removeProduct(id);
        return ResponseEntity.ok("삭제완료");
    }


    // 왜 RequestBody로 id까지 전달 받지 않고,
    // 굳이 PathVariable로 id는 따로 받아오나요?

    // -> RESTFUL 설계 : URL과 요청 Method만으로도 뭐하는지 예측 할 수 있다
    // localhost:8080/product/1 PUT -> product에 1번 자원을 수정하는것
    @PutMapping("/{id}")
    public ResponseEntity<?> putProduct(@PathVariable int id, @Valid @RequestBody ModifyProductReqDto dto){
        productService.modifyProduct(id, dto);
        return ResponseEntity.ok("수정완료");
    }

    @GetMapping("/top3")
    public ResponseEntity<?> top3(){
        return ResponseEntity.ok(productService.getTop3SellingProduct());
    }

    @GetMapping("/{productId}/quantity")
    public ResponseEntity<?> getProductWithQuantities(@PathVariable int productId) {
        return ResponseEntity.ok(productService.getProductQuantityById(productId));
    }
}

