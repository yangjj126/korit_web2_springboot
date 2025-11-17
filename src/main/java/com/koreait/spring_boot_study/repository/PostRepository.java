package com.koreait.spring_boot_study.repository;

import com.koreait.spring_boot_study.entity.Post;
import com.koreait.spring_boot_study.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        return posts.stream()
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
//        // content를 보는것이 중요한건가?
}
