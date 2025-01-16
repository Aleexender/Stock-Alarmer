package org.example.stockAlarmer.global.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public enum AlarmError implements Error {
    PARAMETER_VIOLATION_ERROR("F001", BAD_REQUEST, "올바르지 않은 데이터입니다.");
    private final String code;
    private final HttpStatus status;
    private final String message;

    AlarmError(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return status;
    }
}
