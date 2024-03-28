package com.sopt.Server.repository;

import com.sopt.Server.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("등록한 멤버를 멤버 이름을 통해 조회할 수 있다.")
    void findMemberByName() {
        // given
        Member member = Member.builder()
               .name("윤가든")
               .realAge(20)
               .build();

        Member savedMember = memberJpaRepository.save(member);

        // when
        Optional<Member> foundMember = memberJpaRepository.findByName("윤가든");

        // then
        foundMember.ifPresent(m -> Assertions.assertThat(m).isEqualTo(savedMember));
    }
}