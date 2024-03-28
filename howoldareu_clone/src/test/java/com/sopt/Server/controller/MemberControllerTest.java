package com.sopt.Server.controller;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.MemberPostRequest;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.domain.Member;
import com.sopt.Server.exception.Success;
import com.sopt.Server.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends MockManager{

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    // ai가 나보다 잘짠다..
    @Test
    @DisplayName("멤버를 저장할 수 있다.")
    void saveMember_validRequest_returnsSuccess() {
        // given
        Member member = Member.builder().name("정원").realAge(20).build();
        MemberPostRequest request = new MemberPostRequest("정원", 10);
        MemberGetResponse response = MemberGetResponse.of(member);
        when(memberService.saveMember(request)).thenReturn(response);

        // when
        ApiResponse<MemberGetResponse> result = memberController.saveMember(request);

        // then
        assertEquals(Success.CREATE_MEMBER_SUCCESS.getHttpStatus(), result.getCode());
        assertEquals(Success.CREATE_MEMBER_SUCCESS.getMessage(), result.getMessage());
        assertEquals(response, result.getData());
    }

    @Test
    @DisplayName("멤버를 저장할 수 있다.")
    void saveMemberController() throws Exception {
        // given
        MemberPostRequest request = new MemberPostRequest("정원", 20);
        MemberGetResponse response = new MemberGetResponse(1L, "정원", 20);

        given(memberService.saveMember(any(MemberPostRequest.class)))
                .willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.name").value(response.name()))
                .andExpect(jsonPath("$.data.realAge").value(response.realAge()));
    }

    @Test
    @DisplayName("멤버를 조회할 수 있다.")
    void getAllResultsController() throws Exception {
        // given

        MemberPostRequest request = new MemberPostRequest("정원", 20);
        MemberGetResponse response = new MemberGetResponse(1L, "정원", 20);

        given(memberService.getMember(any(MemberPostRequest.class)))
                .willReturn(response);
        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.name").value(response.name()))
                .andExpect(jsonPath("$.data.realAge").value(response.realAge()));
    }
}