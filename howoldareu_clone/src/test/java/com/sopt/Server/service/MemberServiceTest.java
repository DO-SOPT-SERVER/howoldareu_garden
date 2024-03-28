package com.sopt.Server.service;

import com.sopt.Server.controller.request.MemberPostRequest;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.exception.Error;
import com.sopt.Server.exception.model.CustomException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

//    @Test
//    @DisplayName("멤버 이름을 알면 해당 멤버를 조회할 수 있다.")
//    void getMember() {
//        // given
//        Member member = Member.builder()
//                .name("윤가든")
//                .realAge(20)
//                .build();
//
//        MemberPostRequest request = new MemberPostRequest(
//                "윤가든",
//                20
//        );
//
//        MemberGetResponse response = new MemberGetResponse(
//                1L,
//                "윤가든",
//                20
//        );
//
//        MemberJpaRepository memberJpaRepository = mock(MemberJpaRepository.class);
//
//        given(memberJpaRepository.save(any(Member.class)))
//                .willReturn(member);
//
//        // when
//        MemberGetResponse findMember = memberService.getMember(request);
//        // then
//        Assertions.assertThat(findMember)
//                .extracting( "name", "realAge")
//                .containsExactly(response.name(), response.realAge());
//    }

    @Test
    @DisplayName("존재하지 않는 이름을 입력할 경우 예외가 발생한다")
    void notGetMember() {
        // given
        MemberPostRequest request = new MemberPostRequest(
                "윤가든",
                20
        );

        // when, then
        Assertions.assertThatThrownBy(() -> memberService.getMember(request)).isInstanceOf(CustomException.class)
                .hasMessage(Error.NOT_FOUND_MEMBER_EXCEPTION.getMessage());
    }

    @Test
    @DisplayName("멤버를 등록할 수 있다.")
    void saveMember() {
        // given
        MemberPostRequest request = new MemberPostRequest("윤가든", 20);

        // when
        MemberGetResponse savedMember = memberService.saveMember(request);

        // then
        Assertions.assertThat(savedMember)
                .extracting( "name", "realAge")
                .containsExactly(request.name(), request.age());
    }
}