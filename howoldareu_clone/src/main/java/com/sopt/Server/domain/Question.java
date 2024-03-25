package com.sopt.Server.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "QUESTIONS")
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String questionContent;
    @OneToMany(mappedBy = "answer")
    private List<Answer> answers = new ArrayList<>();
}
