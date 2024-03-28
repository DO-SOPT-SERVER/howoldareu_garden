package com.sopt.Server.service;

import com.sopt.Server.common.AgeEnum;
import com.sopt.Server.controller.response.AllResultsResponseDTO;
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
        assertThat(finalResponse)
                .extracting("resultAge", "title", "content")
                .containsExactlyInAnyOrder(
                        tuple(result.getResultAge(), AgeEnum.FIFTIES.getTitle(), AgeEnum.FIFTIES.getContent()),
                        tuple(result.getResultAge(), AgeEnum.TWENTIES.getTitle(), AgeEnum.TWENTIES.getContent())
                );

    }
}