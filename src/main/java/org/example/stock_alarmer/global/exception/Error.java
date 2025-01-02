package org.example.stock_alarmer.global.exception;

import org.springframework.http.HttpStatus;

public interface Error {
    String getCode();

    String getMessage();

    HttpStatus getHttpStatus();
}
