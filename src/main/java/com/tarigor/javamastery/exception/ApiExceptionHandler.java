package com.tarigor.javamastery.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorMessage entityNotFoundException(ResourceNotFoundException exception) {
        log.error("entityNotFoundException:error -> {}", exception.toString());
        return new ApiErrorMessage(
                LocalDateTime.now(),
                "Resource Not Found",
                exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorMessage validationException(MethodArgumentNotValidException exception) {
        log.error("validationException:error -> {}", exception.getMessage());
        return new ApiErrorMessage(
                LocalDateTime.now(),
                "Validation Error",
                Objects.requireNonNull(exception.getFieldError()).getDefaultMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorMessage handleOtherExceptions(Exception exception) {
        log.error("handleOtherExceptions:error -> {}", exception.getMessage());
        return new ApiErrorMessage(
                LocalDateTime.now(),
                "Internal Server Error Occurred",
                exception.getMessage());
    }
}
