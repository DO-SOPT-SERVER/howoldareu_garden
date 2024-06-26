package com.sopt.Server.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Error {

    // 400 BAD REQUEST
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
    MEMBER_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 회원 요청입니다"),


    // 404 NOT FOUND
    NOT_FOUND_MEMBER_EXCEPTION(HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다"),
    NOT_FOUND_QUESTION_EXCEPTION(HttpStatus.NOT_FOUND, "해당 질문을 찾을 수 없습니다"),
    NOT_FOUND_ANSWER_EXCEPTION(HttpStatus.NOT_FOUND, "해당 답변을 찾을 수 없습니다"),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatus(){
        return httpStatus.value();
    }

    public String getMessage(){
        return message;
    }

}
