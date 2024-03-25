package com.sopt.Server.controller;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.AnswerListRequestDTO;
import com.sopt.Server.controller.response.AllResultsResponseDTO;
import com.sopt.Server.controller.response.ResultResponseDTO;
import com.sopt.Server.exception.Success;
import com.sopt.Server.service.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResultControllerTest {

    @Mock
    private ResultService resultService;

    @InjectMocks
    private ResultController resultController;

    private AnswerListRequestDTO answerListRequestDTO;
    private ResultResponseDTO resultResponseDTO;
    private List<AllResultsResponseDTO> allResultsResponseDTOList;

    @BeforeEach
    void setUp() {
        answerListRequestDTO = new AnswerListRequestDTO();
        resultResponseDTO = new ResultResponseDTO();
        allResultsResponseDTOList = List.of(new AllResultsResponseDTO());
    }

    @Test
    void saveResult() {
        when(resultService.saveResult(answerListRequestDTO)).thenReturn(resultResponseDTO);

        ApiResponse<ResultResponseDTO> response = resultController.saveResult(answerListRequestDTO);

        verify(resultService, times(1)).saveResult(answerListRequestDTO);
        assertEquals(response.getData(), resultResponseDTO);
    }

    @Test
    void getAllResults() {
        when(resultService.getAllResults(anyLong())).thenReturn(allResultsResponseDTOList);

        ApiResponse<List<AllResultsResponseDTO>> response = resultController.getAllResults(1L);

        verify(resultService, times(1)).getAllResults(1L);
        assertEquals(response.getData(), allResultsResponseDTOList);
    }
}