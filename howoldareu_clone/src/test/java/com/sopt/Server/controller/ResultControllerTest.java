package com.sopt.Server.controller;


import com.sopt.Server.common.AgeEnum;
import com.sopt.Server.controller.request.AnswerListRequestDTO;
import com.sopt.Server.controller.request.AnswerRequestDTO;
import com.sopt.Server.controller.response.ResultResponseDTO;
import com.sopt.Server.domain.Member;
import com.sopt.Server.domain.Result;
import com.sopt.Server.service.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ResultControllerTest extends MockManager{

    @InjectMocks
    private ResultController resultController;

    @Mock
    private ResultService resultService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(resultController).build();
    }

    @Test
    @DisplayName("결과를 저장할 수 있다.")
    void saveResultController() throws Exception {
      // given
        AnswerListRequestDTO request = new AnswerListRequestDTO(
                "정원",
                List.of(
                        new AnswerRequestDTO(1L, true),
                        new AnswerRequestDTO(2L, true)
                ));
        ResultResponseDTO response = ResultResponseDTO.of("정원", 20, AgeEnum.THIRTIES);

        // BBDMockito
//        given(resultService.saveResult(any(AnswerListRequestDTO.class)))
//                .willReturn(response);
        doReturn(response).when(resultService)
                .saveResult(any(AnswerListRequestDTO.class));
      // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/result")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        );

      // then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.name", response.name()).exists())
                .andExpect(jsonPath("$.data.resultAge").value(response.resultAge()))
                .andExpect(jsonPath("$.data.title").value(response.title()))
                .andExpect(jsonPath("$.data.content").value(response.content()));
    }

    @Test
    @DisplayName("결과를 조회할 수 있다.")
    void getAllResultsController() throws Exception {
        // given
        Member member = Member.builder()
                .name("정원")
                .realAge(20)
                .build();

        Result request = Result.builder()
                .resultAge(20)
                .member(member)
                .build();

        ResultResponseDTO response = ResultResponseDTO.of(
                "정원",
                20,
                AgeEnum.TWENTIES
        );

        doReturn(List.of(response)).when(resultService)
                .getAllResults(any(Long.class));
        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/result/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data[0].name").value(response.name()))
                .andExpect(jsonPath("$.data[0].resultAge").value(response.resultAge()))
                .andExpect(jsonPath("$.data[0].title").value(response.title()))
                .andExpect(jsonPath("$.data[0].content").value(response.content()));
    }
}