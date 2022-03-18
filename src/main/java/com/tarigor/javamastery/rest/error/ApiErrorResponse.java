package com.tarigor.javamastery.rest.error;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
public class ApiErrorResponse {

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
    private String detail;
    private LocalDateTime timeStamp;

    public ApiErrorResponse withStatus(HttpStatus status) {
        this.httpStatus = httpStatus;
        return this;
    }

    public ApiErrorResponse withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ApiErrorResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiErrorResponse withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public ApiErrorResponse atTime(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public ApiErrorResponse build() {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.httpStatus = this.httpStatus;
        apiErrorResponse.errorCode = this.errorCode;
        apiErrorResponse.detail = this.detail;
        apiErrorResponse.message = this.message;
        apiErrorResponse.timeStamp = this.timeStamp;
        return apiErrorResponse;
    }
}
