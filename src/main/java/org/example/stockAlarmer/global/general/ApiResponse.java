package org.example.stockAlarmer.global.general;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.example.stockAlarmer.global.exception.Error;
import org.springframework.http.ResponseEntity;

@JsonPropertyOrder({"code", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>( String code, String message, T data) {

    public static ApiResponse<?> ok() {
        return ok(null);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>("S001", "성공적으로 요청을 수행했습니다.", data);
    }

    public static ResponseEntity<?> error(Error error) {
        ApiResponse<?> response = new ApiResponse<>(error.getCode(), error.getMessage(), null);
        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }
}
