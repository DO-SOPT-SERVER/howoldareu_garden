package com.sopt.Server.service;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.MemberPostRequest;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.domain.Member;
import com.sopt.Server.exception.Error;
import com.sopt.Server.exception.Success;
import com.sopt.Server.exception.model.CustomException;
import com.sopt.Server.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public MemberGetResponse getMember(MemberPostRequest request) {
        Member member = getMemberByName(request.name());
        return MemberGetResponse.of(member);
    }

    @Transactional
    public MemberGetResponse saveMember(MemberPostRequest request) {
        Member member = Member.builder()
                .name(request.name())
                .realAge(request.age())
                .build();

        memberJpaRepository.save(member);

        return MemberGetResponse.of(member);
    }

    private Member getMemberByName(String name) {
        return memberJpaRepository.findByName(name)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_MEMBER_EXCEPTION, Error.NOT_FOUND_MEMBER_EXCEPTION.getMessage()));
    }
}
