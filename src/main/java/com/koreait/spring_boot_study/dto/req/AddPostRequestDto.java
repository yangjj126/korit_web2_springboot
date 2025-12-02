package com.koreait.spring_boot_study.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@ToString
@NoArgsConstructor
public class AddPostRequestDto {

    @NotBlank(message = "비우면안됌")
    @Size(max=100, message = "제목은 100자 초과안됨")
    private String title;

    @Size(max=5000, message = "내용은 5000자 초과안됨")
    private String content;
}



