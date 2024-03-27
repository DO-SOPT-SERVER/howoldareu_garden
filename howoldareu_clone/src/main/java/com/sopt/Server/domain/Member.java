package com.sopt.Server.domain;

import com.sopt.Server.exception.Error;
import com.sopt.Server.exception.model.CustomException;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBERS")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private int realAge;

    @Builder
    public Member(String name, int realAge) {
        validateName(name);
        this.name = name;
        this.realAge = realAge;
    }

    private void validateName(String name) {
        if (!name.matches("^[가-힣]*$")) {
            throw new CustomException(Error.MEMBER_VALIDATION_EXCEPTION, Error.MEMBER_VALIDATION_EXCEPTION.getMessage());
        }
    }
}
