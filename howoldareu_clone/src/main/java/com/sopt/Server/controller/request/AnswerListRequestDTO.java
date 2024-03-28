package com.sopt.Server.controller.request;

import lombok.Builder;

import java.util.List;

@Builder
public record AnswerListRequestDTO(String name, List<AnswerRequestDTO> results) {
}
