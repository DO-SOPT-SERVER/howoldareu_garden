package com.sopt.Server.service;

import com.sopt.Server.common.AgeEnum;
import com.sopt.Server.controller.request.AnswerListRequestDTO;
import com.sopt.Server.controller.request.AnswerRequestDTO;
import com.sopt.Server.controller.response.AllResultsResponseDTO;
import com.sopt.Server.controller.response.ResultResponseDTO;
import com.sopt.Server.domain.Answer;
import com.sopt.Server.domain.Member;
import com.sopt.Server.domain.Question;
import com.sopt.Server.domain.Result;
import com.sopt.Server.exception.Error;
import com.sopt.Server.exception.model.CustomException;
import com.sopt.Server.repository.AnswerJpaRepository;
import com.sopt.Server.repository.MemberJpaRepository;
import com.sopt.Server.repository.QuestionJpaRepository;
import com.sopt.Server.repository.ResultJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {
    private final ResultJpaRepository resultJpaRepository;
    private final AnswerJpaRepository answerJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final QuestionJpaRepository questionJpaRepository;

    @Transactional
    public ResultResponseDTO saveResult(AnswerListRequestDTO request) {
        Member member = getMember(request.name());
        int memberAge = member.getRealAge();

        for(AnswerRequestDTO result : request.results()){
            Question question = getQuestion(result.questionId());
            Answer answer = getAnswer(question, result.answerType());
            memberAge += answer.getAnswerScore();
        }

        AgeEnum ageEnum = getAgeEnum(memberAge);

        Result result = Result.builder()
                .member(member)
                .resultAge(memberAge)
                .build();

        resultJpaRepository.save(result);

        return ResultResponseDTO.of(member.getName(), memberAge, ageEnum);
    }

    public List<AllResultsResponseDTO> getAllResults(Long memberId) {
        List<Result> resultList  = resultJpaRepository.findAllByMemberIdOrderByIdDesc(memberId);
        List<AllResultsResponseDTO> answer = new ArrayList<>();

        //멤버의 result 모두 갖고옴 이것을 각각의 result마다 allresultsresponsedto만들어야 해
        for(Result result : resultList) {
            AgeEnum ageEnum = getAgeEnum(result.getResultAge());
            AllResultsResponseDTO dto = AllResultsResponseDTO.of(result, ageEnum);
            answer.add(dto);
        }

        return answer;
    }

    private Member getMember(String name) {
        return memberJpaRepository.findByName(name)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_MEMBER_EXCEPTION, Error.NOT_FOUND_MEMBER_EXCEPTION.getMessage()));
    }

    private Question getQuestion(Long questionId) {
        return questionJpaRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_QUESTION_EXCEPTION,Error.NOT_FOUND_QUESTION_EXCEPTION.getMessage()));
    }

    private Answer getAnswer(Question question, boolean answerType) {
        return answerJpaRepository.findByQuestionAndAnswerType(question, answerType)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_ANSWER_EXCEPTION,Error.NOT_FOUND_ANSWER_EXCEPTION.getMessage()));
    }

    private String getStringDate(LocalDateTime time) {
        return time.getMonthValue() + "월 " + time.getDayOfMonth() + "일";
    }

    private AgeEnum getAgeEnum(int age){
        if(age < 20)
            return AgeEnum.TEENAGER;
        else if(age < 30)
            return AgeEnum.TWENTIES;
        else if(age < 40)
            return AgeEnum.THIRTIES;
        else if(age < 50)
            return AgeEnum.FORTIES;
        else
            return AgeEnum.FIFTIES;
    }

}
