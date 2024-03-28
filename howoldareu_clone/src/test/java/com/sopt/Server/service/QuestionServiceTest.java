package com.sopt.Server.service;

import com.sopt.Server.controller.response.GetQuestionResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class QuestionServiceTest {

    @Mock
    private QuestionService questionService;

    @Test
    @DisplayName("질문을 모두 조회할 수 있다.")
    void getQuestionResponses() {
      // given
        List<GetQuestionResponseDTO> response = List.of(
                new GetQuestionResponseDTO(1L, "Question 1"),
                new GetQuestionResponseDTO(2L, "Question 2")
        );

        given(questionService.getQuestionResponseDTOList())
                .willReturn(response);

      // when
        List<GetQuestionResponseDTO> result = questionService.getQuestionResponseDTOList();

      // then
        assertThat(result)
                .extracting("questionId", "questionContent")
                .containsExactlyInAnyOrder(
                        tuple(1L, "Question 1"),
                        tuple(2L, "Question 2")
                );

    }
}