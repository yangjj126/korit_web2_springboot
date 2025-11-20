package com.koreait.spring_boot_study.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddProductReqDto {
    // 검증 실패시 => 예외를 던진다
    // 예외에 들어갈 메시지 -> message 속성
    @NotBlank(message = "이름은 비울 수 없습니다.")
    @Size(max = 50, message = "50글자 이상은 지을 수 없습니다")
    private String name;

    @Positive(message = "무조건 가격양수")
    private int price;
    // 얘내들의 예외를 처리해주는 라이브러리가 필요함

    /*
    < 스프링 validation 라이브러리 사용법 >
    1. 문자열
    @NotBlank : null, "" , "        " 모두 허용 안된다
    @NotEmpty : null, "" , 허용안하겠다
    @Size(min=, max=100) 문자열 길이 제한
    @Email 이메일 형식 검사(정규식 기반..)
    2. 숫자
    @Min(value = )
    @Max(value = )
    @Positive 양수만 허용
    @Negative 음수만 허용
    3. 객체
    @Valid // 내부객체를 검증하라
    @NotNull // null만 금지

     */
}
