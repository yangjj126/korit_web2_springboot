package com.koreait.spring_boot_study.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModifyPostReqDto {
    @NotBlank(message = "비우면안됌")
    @Size(max=100, message = "제목은 100자 초과안됨")
    private String title;

    @Size(max=5000, message = "내용은 5000자 초과안됨")
    private String content;
}
