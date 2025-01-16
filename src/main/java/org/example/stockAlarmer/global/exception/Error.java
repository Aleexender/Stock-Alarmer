package org.example.stockAlarmer.global.exception;

import org.springframework.http.HttpStatus;

public interface Error {
    String getCode();

    String getMessage();

    HttpStatus getHttpStatus();
}
