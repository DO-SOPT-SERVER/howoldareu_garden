package com.sopt.Server.controller.response;

import com.sopt.Server.common.AgeEnum;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ResultResponseDTO(
        String name,
        int resultAge,
        String title,
        String content,
        String imgUrl1,
        String imgUrl2) {
    public static ResultResponseDTO of(String name, int resultAge, AgeEnum ageEnum) {
        return ResultResponseDTO.builder()
                .name(name)
                .resultAge(resultAge)
                .title(ageEnum.getTitle())
                .content(ageEnum.getContent())
                .imgUrl1(ageEnum.getImageUrl1())
                .imgUrl2(ageEnum.getImageUrl2())
                .build();
    }
}
