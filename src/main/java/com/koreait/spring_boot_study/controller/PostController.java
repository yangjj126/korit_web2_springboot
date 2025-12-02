package com.koreait.spring_boot_study.controller;

import com.koreait.spring_boot_study.Service.PostService;
import com.koreait.spring_boot_study.dto.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {
    // 생성될때, 정해지고, 불변한다
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/title/{id}")
    public ResponseEntity<?> getPostTitleById(@PathVariable int id){
        String title = postService.getPostTitleById(id);
        return ResponseEntity.ok(title);
        // 예외를 try-catch를 디스패치가 한다
    }

    // 전체게시글의 제목 조회
    @GetMapping("/title/all")
    public ResponseEntity<?> getAllPostTitles() {
        List<String> posts = postService.getAllPostNames();
        return ResponseEntity.ok(posts);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable int id) {
        PostReqDto dto = postService.getPostById(id);
        return ResponseEntity.ok(id);
    }


    // 1. 레파지토리부터 작성
    // 1. 단건 추가 컨트롤러 -> 서비스 -> 레파지토리 코드 작성
    // +  Validation을 사용해봅시다!
    @PostMapping("/add")
    public ResponseEntity<?> addPost(
            @Valid @RequestBody AddPostRequestDto dto // 잭슨이 dto를 만들때, NoArgsController
    ){
        postService.addPost(dto);
        // 못적음
        return ResponseEntity.status(HttpStatus.CREATED).body("등록성공");
    }


    // 1. id를 받아서, -> 게시글을 삭제하는 컨트롤러 -> 서비스 -> 레파지토리
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(
            @PathVariable int id
    ){
        postService.deletePost(id);
        return ResponseEntity.ok("삭제완료");
    }

    // 2. id를 받고, pathVariable을 통해서, title과 content를 받아와서, 타당한지 판단하는 코드
    // 그 리스트를 업데이트 해주는 코드
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyPost(@PathVariable int id, @Valid @RequestBody ModifyPostReqDto dto){
        postService.updatePost(id, dto);
        return ResponseEntity.ok("완료");
    }


    // 수정요청 PUT, PATCH
    // PUT -> 전체데이터를 갈아끼우겠다(title, content) 제일 많이 씀

    // PATCH -> 일부 데이터를 갈아끼우겠다(content) -> null을 허용해야 하는 경우가 많음

    // CRUD완료..
}
