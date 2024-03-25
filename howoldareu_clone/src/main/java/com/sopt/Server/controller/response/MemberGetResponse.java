package com.sopt.Server.controller.response;


import com.sopt.Server.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MemberGetResponse(
        Long memberId,
        String name,
        int realAge) {
    public static MemberGetResponse of(Member member) {
        return MemberGetResponse.builder()
                .memberId(member.getId())
                .name(member.getName())
                .realAge(member.getRealAge())
                .build();
    }
}
