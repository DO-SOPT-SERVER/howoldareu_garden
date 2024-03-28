package com.sopt.Server.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "QUESTIONS")
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String questionContent;

    @Builder
    public Question(String questionContent) {
        this.questionContent = questionContent;
    }

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();
}
