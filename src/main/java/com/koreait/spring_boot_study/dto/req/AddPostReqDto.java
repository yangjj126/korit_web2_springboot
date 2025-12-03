package com.koreait.spring_boot_study.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddPostReqDto {

    @NotBlank(message = "비우면안됌")
    @Size(max=100, message = "제목은 100자 초과안됨")
    private String title;

    @Size(max=5000, message = "내용은 5000자 초과안됨")
    private String content;
}

// dto가 있는 이유는, 나중에 service 에서 dto를 entity로 변환시켜 주는데.
// 만약 dto에서 문제가 발생했을때, service선에서 수정할수 있기 때문이다,
// repository에서 굳이 수정이나 바꿔주지 않아도 된다



