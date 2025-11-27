package com.koreait.spring_boot_study.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class Top3SellingProductResDto {
    private String productName;
    private int totalScoreCount;

    // List<모델> -> List<DTO> 변환 (서비스에서)
    // map사용!

    // 모델 -> Dto 변환 메서드
    public static Top3SellingProductResDto from(Top3SellingProductResDto model) {
        return new Top3SellingProductResDto(
                model.productName,
                model.getTotalScoreCount()
                );
    }
}
