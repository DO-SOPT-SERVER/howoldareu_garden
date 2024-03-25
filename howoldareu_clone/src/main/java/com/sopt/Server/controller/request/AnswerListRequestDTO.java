package com.sopt.Server.controller.request;

import java.util.List;

public record AnswerListRequestDTO(String name, List<AnswerRequestDTO> results) {
}
