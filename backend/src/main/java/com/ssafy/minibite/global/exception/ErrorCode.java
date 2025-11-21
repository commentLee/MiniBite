package com.ssafy.minibite.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    DUPLICATE_USER_ID(HttpStatus.BAD_REQUEST, "중복된 ID입니다"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 올바르지 않습니다"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다"),
    VALIDATION_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, "필수 항목이 누락되었습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

