package com.sopt.Server.controller.response;

import com.sopt.Server.common.AgeEnum;
import com.sopt.Server.domain.Result;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)

public record AllResultsResponseDTO(
        Long id,
        int resultAge,
        String title,
        String content,
        String imgUrl1,
        String imgUrl2) {
    public static AllResultsResponseDTO of(Result result, AgeEnum ageEnum) {
        return new AllResultsResponseDTO(result.getId(),
                result.getResultAge(),
                ageEnum.getTitle(),
                ageEnum.getContent(),
                ageEnum.getImageUrl1(),
                ageEnum.getImageUrl2()
        );
    }
}