package com.sopt.Server.controller.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetQuestionResponseDTO(
        Long questionId,
        String questionContent) {
    public static GetQuestionResponseDTO of(Long questionId, String questionContent) {
        return GetQuestionResponseDTO.builder()
                .questionId(questionId)
                .questionContent(questionContent)
                .build();
    }
}
