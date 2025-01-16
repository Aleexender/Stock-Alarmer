package org.example.stockAlarmer.global.exception;

import org.springframework.http.HttpStatus;

import java.lang.Error;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public enum StockError implements Error {
    PARAMETER_VIOLATION_ERROR("S001",INTERNAL_SERVER_ERROR,"올바르지 않은 데이터입니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;

    StockError(String code, HttpStatus status, String message) {
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
