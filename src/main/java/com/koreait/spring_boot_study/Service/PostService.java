package com.koreait.spring_boot_study.Service;

import com.koreait.spring_boot_study.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    // 포랩과 서비스 연결됨니다


    // 전체조회
    public List<String> getAllPostTitleName(){
    // 다가져온다음에, 음, 거기서 map 돌면서 이름만 뽑고, 그것들 다모아서 리스트로 만들기
        return postRepository.findAllPosts()
                .stream()
                .map(Post -> Post.getTitle())
                .collect(Collectors.toList());
    }
    //    List<String> postTitles = postRepository.findAllPosts() // 전체 다 리턴받았고,
    //            .stream().map(Post -> Post.getTitle())
    //            .collect(Collectors.toList());
    //        return postTitles;
    //
    //    // 하나만 조회하기
    //    public String getPostTitle(int id){
    //        String targetTitle = postRepository.findPostById(id);
    //        return targetTitle;
    //    }

    // 게시글 하나 조회 : isEmpty -> 정석) 예외를 던져야함

}
