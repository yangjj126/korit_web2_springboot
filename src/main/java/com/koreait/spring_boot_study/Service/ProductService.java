package com.koreait.spring_boot_study.Service;

import com.koreait.spring_boot_study.dto.AddProductReqDto;
import com.koreait.spring_boot_study.dto.ModifyProductReqDto;
import com.koreait.spring_boot_study.dto.ProductQuantityResDto;
import com.koreait.spring_boot_study.dto.Top3SellingProductResDto;
import com.koreait.spring_boot_study.entity.OrderDetail;
import com.koreait.spring_boot_study.entity.Product;
import com.koreait.spring_boot_study.exception.ProductNotFoundException;
import com.koreait.spring_boot_study.exception.ProductinsertExeption;
import com.koreait.spring_boot_study.model.Top3SellingProduct;
import com.koreait.spring_boot_study.repository.ProductRepo;
import com.koreait.spring_boot_study.repository.impl.ProductRepository;
import com.koreait.spring_boot_study.repository.mapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

//    private ProductRepo productRepository;
    private ProductMapper productRepository;

    @Autowired
    public ProductService(ProductMapper productRepository) {
        this.productRepository = productRepository;
    }
    // 생성자 조져주고,


    // 1. 전체조회(이름만)
    public List<String> getAllProductNames() {
        List<String> productNames = productRepository.findAllProducts()
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


    // Top3 상품들 리턴해주는 메서드 ( model을 리턴하면 안됨 )
    public List<Top3SellingProductResDto> getTop3SellingProduct(){
        List<Top3SellingProduct> results = productRepository.findTop3SellingProducts();
        List<Top3SellingProductResDto> outputs = new ArrayList<>();
        for(Top3SellingProduct result : results){
            Top3SellingProductResDto dto
                    = Top3SellingProductResDto.from(result);

            outputs.add(dto);
        }
        return outputs;
    }

    public List<ProductQuantityResDto> getProductQuantityById(int productId){
        // Product 객체를 가져옴 ( orderDetails 필드(list)를 mybatis가 알아서 채워옴 )
        Product product = productRepository
                .findProductWithQuantities(productId);
        // 만약, A entity가 B를 가지고 있고,
        // B entity가 A를 가지고 있을 수 있음. (양방향)
        // a.getB().getA().getA()....... -> 자바에서는 문제가 안됨
        // -> 양방향설정을 왠만하면, 쓰지 말자

        // 옵셔녈이 아니라서 null 채크를 해준다
        // product가 null이 아니라, 필드에 있는 List<OrderDetail>이 null이면
        if (product == null || product.getOrderDetails() == null){
            return List.of(); // 비어있는 리스트 리턴
        }

        List<ProductQuantityResDto> resultData = null;
        // stream Api 사용하는 버젼
        resultData = product.getOrderDetails() // List<OrderDetail>
                .stream()  // Stream<OrderDetail>
                .map(od -> new ProductQuantityResDto(
                        // od.getProduct() -> xml에 정의하지 않았기 때문에, null(단방향)
                        product.getName(),
                        product.getPrice(),
                        od.getQuantity()
                )) // Stream<ProductQuantityResDto>
                .collect(Collectors.toList());

//        // for 문을 사용하는 버젼
//        for(OrderDetail od : product.getOrderDetails()) {
//            ProductQuantityResDto dto = new ProductQuantityResDto(
//                    product.getName(),
//                    product.getPrice(),
//                    od.getQuantity()
//            );
//            resultData.add(dto);
//        }

        // 둘중 하나를 택일 해서 그걸 사용하면 된다
        return resultData;
    }
}
