package com.tarigor.javamastery.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiErrorMessage {
    private LocalDateTime timeStamp;
    private String message;
    private String errorDetails;
}
