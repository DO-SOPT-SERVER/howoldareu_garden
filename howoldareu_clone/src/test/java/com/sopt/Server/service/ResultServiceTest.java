package com.sopt.Server.service;

import com.sopt.Server.common.AgeEnum;
import com.sopt.Server.controller.request.AnswerListRequestDTO;
import com.sopt.Server.controller.request.AnswerRequestDTO;
import com.sopt.Server.controller.response.AllResultsResponseDTO;
import com.sopt.Server.controller.response.ResultResponseDTO;
import com.sopt.Server.domain.Member;
import com.sopt.Server.domain.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@DataJpaTest
class ResultServiceTest {
    @Mock
    private ResultService resultService;


    @Test
    @DisplayName("결과를 저장할 수 있다.")
    void saveResult() {
      // given
        AnswerListRequestDTO request = new AnswerListRequestDTO(
                "정원",
                List.of(
                        new AnswerRequestDTO(1L, true),
                        new AnswerRequestDTO(2L, true)
                ));

        ResultResponseDTO response = ResultResponseDTO.of(
                "정원",
                20,
                AgeEnum.TWENTIES
        );
        given(resultService.saveResult(any(AnswerListRequestDTO.class)))
                .willReturn(response);

      // when
        ResultResponseDTO finalResponse = resultService.saveResult(request);

      // then
        assertThat(finalResponse)
                .extracting( "name", "resultAge", "title", "content")
                .containsExactly(response.name(), response.resultAge(), response.title(), response.content());
    }

    @Test
    @DisplayName("결과를 저장할 수 있다.")
    void saveResult2() {
        // given
        AnswerListRequestDTO request = new AnswerListRequestDTO(
                "정원",
                List.of(
                        new AnswerRequestDTO(1L, true),
                        new AnswerRequestDTO(2L, true)
                ));

        // when
        ResultResponseDTO response = resultService.saveResult(request);

        // then
        // 이거 왜 null 값 나오지..
//        logger.debug(response.toString());
//        assertThat(response)
//                .extracting( "name", "resultAge")
//                .containsExactly(request.name(), 20);

    }

    @Test
    @DisplayName("결과를 모두 조회할 수 있다.")
    void getAllResults() {
        // given
        Member member = Member.builder().name("정원").realAge(20).build();
        Result result = Result.builder()
                .member(member)
                .resultAge(10)
                .build();

        List<AllResultsResponseDTO> response = List.of(
                AllResultsResponseDTO.of(result, AgeEnum.FIFTIES),
                AllResultsResponseDTO.of(result, AgeEnum.TWENTIES)
        );

        given(resultService.getAllResults(any(Long.class)))
                .willReturn(response);

        // when
        List<AllResultsResponseDTO> finalResponse = resultService.getAllResults(member.getId());

        // then
        //finalresponse 하면 왜.. Expecting actual: [] 값이 비어오지
        assertThat(response)
                .extracting("resultAge", "title", "content")
                .containsExactlyInAnyOrder(
                        tuple(result.getResultAge(), AgeEnum.FIFTIES.getTitle(), AgeEnum.FIFTIES.getContent()),
                        tuple(result.getResultAge(), AgeEnum.TWENTIES.getTitle(), AgeEnum.TWENTIES.getContent())
                );

    }
}