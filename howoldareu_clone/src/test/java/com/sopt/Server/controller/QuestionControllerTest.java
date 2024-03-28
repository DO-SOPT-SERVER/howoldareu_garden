package com.sopt.Server.controller;

import com.sopt.Server.controller.response.GetQuestionResponseDTO;
import com.sopt.Server.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuestionControllerTest extends MockManager {
    @InjectMocks
    private QuestionController questionController;

    @Mock
    QuestionService questionService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    @DisplayName("질문을 조회할 수 있다.")
    void getQuestionController() throws Exception {
        // given
        GetQuestionResponseDTO response1 = GetQuestionResponseDTO.of(
                1L,
                "Question 1"
        );
        GetQuestionResponseDTO response2 = GetQuestionResponseDTO.of(
                2L,
                "Question 2"
        );

//        given(questionService.getQuestionResponseDTOList()).willReturn(List.of(response1, response2));
        doReturn(List.of(response1, response2)).when(questionService)
               .getQuestionResponseDTOList();

        // when
        ResultActions resultActions = mockMvc.perform(get("/question"));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data[0].questionId").value(response1.questionId()))
                .andExpect(jsonPath("$.data[0].questionContent").value(response1.questionContent()))
                .andExpect(jsonPath("$.data[1].questionId").value(response2.questionId()))
                .andExpect(jsonPath("$.data[1].questionContent").value(response2.questionContent()));
    }
}