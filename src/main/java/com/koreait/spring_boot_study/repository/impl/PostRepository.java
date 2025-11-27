package com.koreait.spring_boot_study.repository.impl;

import com.koreait.spring_boot_study.entity.Post;
import com.koreait.spring_boot_study.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PostRepository {
    // CRUD(생성, 조회, 수정, 삭제)
    // DB 대용 필드 - SQL쿼리로 DB에서 데이터를 받아옴, (주로 List로)
    private List<Post> posts = new ArrayList<>(
            Arrays.asList(
             new Post(1, "일이커", "집가고 싶다"),
             new Post(2, "이이커", "집가고 싶다"),
             new Post(3, "삼이커", "집가고 싶다"),
             new Post(4, "사이커", "집가고 싶다"),
             new Post(5, "오이커", "집가고 싶다")
            )
    );

    // 전체 다 조회
    public List<Post> findAllPosts(){
        return posts;
    }

    public Optional<Post> findPostById(int id){
        // Optional은 유틸리티이기 때문에, 굳이 @Bean을 안써도 된다
        return posts
                .stream()
                .filter(post -> post.getId() == id)
                .findFirst();
        // 객체가 있으면, 객체를 Optional로 감싸서 리턴
        // 없으면, null을 Optional로 감싸서 리턴
    }

//        stream()을 사용해서 해당 id와 같은 그 상품의 이름을 리턴하도록 한다
//        Optional<Post> optionalPost = posts.stream()
//                .filter(Post ->Post.getId() == id)
//                .findFirst();
//                  첫번째 객체를 찾았다
//
//        if (optionalPost.isEmpty()){
//            return "해당 id는 존재하지 않습니다";
//        }
//
//        String targetTitle = optionalPost.get().getTitle();
//
//        return targetTitle;
//        -- content를 보는것이 중요한건가? --


// 포스트추가
// 아이디의 최대값을 일단 추적 한다음에
// 그 id를 가지고,  새로운 post 객체형태를 만들어준다
    public int insertPost(String title, String content){
        int maxId = posts
                .stream()
                .map(post -> post.getId())
                .max((id1, id2) -> id1 - id2)
                .get();
        // -- auto_increment --  기능

        Post post = new Post(maxId+1, title, content);
        posts.add(post);  // sql insert 쿼리

        return 1;
    }




//    상품 추가
//    public int insertProduct(String name, int price){
//        // 아이디 최대값 추적
//        int maxId = products.stream()
//                .map(product -> product.getId())
//                .max((id1, id2) -> id1 - id2)
//                .get();
//        // 저번에 배운거 복습해야겠지 ,,, 함수형메서드 배운것들말이여
//
//        // for문 사용
//        int maxId2 = 0;
//        for (Product product : products){ // product 하나씩 꺼내오고,
//            if (product.getId() > maxId){ // 비교
//                maxId2 = product.getId(); // 꺼내온값이 크면, 그것으로 업데이트
//            }
//        }
//
//        Product product = new Product(maxId + 1,  name, price);
//        products.add(product);
//        return 1; //
//    }



    // 상품삭제하는 메서드 만들기
    public int deletePostById(int id){
        Optional<Post> target = posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst();
        //  Optional<Post> == Post 라는 걸 들고 있거나, null 아거나 이다,

        if (target.get() == null){
            return 0;
        }

        posts.remove(target.get());
        log.info("삭제된 데이터 : {}" , target.get());
        return 1;
    }

    // 상품 업데이트 하는 메서드
    public int updatePost(int id, String title, String content){
        // 먼저 id가 target이랑 일치하는지 확인한다
        Post target = null;

        for (Post post : posts){
            if (post.getId() == id){
                target = post; // 우선 taget이 아이디가 같은 post 이다
                break; // findFirst()
            }
        }

        if (target == null){
            return 0;
        }
        // 해당 id가 존재하지 않으면, 그냥 아니뎌

        int index = posts.indexOf(target);
        // target의 index를 이제 알게된거임

        Post newpost = new Post(id, title, content); // 기존 객체 : tartget
        // 매개변수로 전달받은 id, title, content를 통해서
        // 객체를 새로 생성
        // 외부에서 가져온 데이터로 객체를 새로 생성 (new Post)
        // target을 new Post로 바꿔준다


        posts.set(index, newpost);
        // 원래 index(target이 있던 자리)에  새로 만든, newpost를 덮어씌어 주세요
        // set을 통해서, 해당 index의 title, content를 다 뜯어 고쳐야한다
        return 1;
    }

    public static interface ProductRepo {
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
    }
}
