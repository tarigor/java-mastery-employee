package com.tarigor.javamastery.rest.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private LocalDateTime timeStamp;
    private HttpStatus httpStatus;
    private String message;
    private List error;
}
