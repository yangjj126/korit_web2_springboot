package com.koreait.spring_boot_study.Service;

import com.koreait.spring_boot_study.dto.AddPostRequestDto;
import com.koreait.spring_boot_study.dto.ModifyPostReqDto;
import com.koreait.spring_boot_study.dto.ModifyProductReqDto;
import com.koreait.spring_boot_study.dto.PostReqDto;
import com.koreait.spring_boot_study.entity.Post;
import com.koreait.spring_boot_study.exception.PostInsertException;
import com.koreait.spring_boot_study.exception.PostNotFoundException;
import com.koreait.spring_boot_study.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<String> getAllPostNames(){
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



    // 게시글 하나 조회 : isEmpty -> 정석) 예외를 던져야함(커스텀예외)
    public String getPostTitleById(int id){
        Optional<Post> postOptional = postRepository.findPostById(id);
        // 옵셔널을 언패킹하는 방법(예외도 같이 던질 수 있음)
        // 옵셔널.orElseThrow() :
        // Optional에 포장된 객체가 null이 아니면, post 변수에 담고,
        // null 이면, 예외를 던지세요
        Post post = postOptional.orElseThrow(
                () -> new PostNotFoundException("게시글을 찾을 수 없음")
        );
        String title =  post.getTitle();
        return title;
    }




    // 게시글 전체 리턴
    public List<PostReqDto> getAllPost() {
        // 나머지 못적은것 적어주기
        return postRepository.findAllPosts() // List<Post>
                .stream()
                .map(post -> new PostReqDto(post.getTitle(),
                        post.getContent())) // Stream<PostResDto>
                        .collect(Collectors.toList()); // List<PostResDto>
    }

    // 게시글 단건 조회
    public PostReqDto getPostById(int id){
        Post post = postRepository.findPostById(id) // Optional<Post>
                .orElseThrow(
                        () -> new PostNotFoundException("게시글을 찾을 수 없음")
                );
        return new PostReqDto(post.getTitle(), post.getContent());
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
    public void addPost(@Valid AddPostRequestDto dto){
        int successCount = postRepository.insertPost(dto.getTitle(), dto.getContent());

        if (successCount <= 0){
            throw new PostInsertException("게시물등록x");
        }
    }


    public void deletePost(int id){
        int successCount = postRepository.deletePostById(id);

        if (successCount <= 0){
            throw new PostNotFoundException("삭제할거 없어");
        }
    }

    public void updatePost(int id, @Valid ModifyPostReqDto dto){ // ModifyPostReqDto를 통해서 만든 entity들을 이용할거다
        // 그 이유는 해당 엔티티에 조건을 걸어줄거기 때문이다
        int successCount = postRepository.updatePost(id, dto.getTitle(), dto.getContent());

        if (successCount <= 0){
            throw new PostNotFoundException("업데이트불가능");
        }
    }
}
