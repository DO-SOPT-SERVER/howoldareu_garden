package com.sopt.Server.repository;

import com.sopt.Server.domain.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class QuestionJpaRepositoryTest {

    @Autowired
    QuestionJpaRepository questionJpaRepository;

    @Test
    @DisplayName("질문을 모두 찾을 수 있다.")
    void questionFindAll() {
        Question question = Question.builder()
                .questionContent("Q1")
                .build();

        questionJpaRepository.save(question);
        List<Question> foundQuestions = questionJpaRepository.findAll();

        Assertions.assertThat(foundQuestions).hasSize(1);
    }

}