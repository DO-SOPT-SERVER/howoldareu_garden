package com.sopt.Server.controller;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.MemberPostRequest;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.exception.Success;
import com.sopt.Server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ApiResponse<MemberGetResponse> saveMember(@RequestBody MemberPostRequest request) {
        MemberGetResponse response = memberService.saveMember(request);
        return ApiResponse.success(Success.CREATE_MEMBER_SUCCESS, response);
    }

    @GetMapping("/member")
    public ApiResponse<MemberGetResponse> getMember(@RequestBody MemberPostRequest request) {
        MemberGetResponse response = memberService.getMember(request);
        return ApiResponse.success(Success.CREATE_MEMBER_SUCCESS, response);
    }

}
