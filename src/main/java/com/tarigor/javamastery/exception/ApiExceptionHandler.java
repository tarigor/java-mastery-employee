package com.tarigor.javamastery.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
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
    public ApiErrorMessage methodArgumentValidationException(MethodArgumentNotValidException exception) {
        log.error("methodArgumentValidationException:error -> {}", exception.getMessage());
        return new ApiErrorMessage(
                LocalDateTime.now(),
                "Validation Error",
                Objects.requireNonNull(exception.getFieldError()).getDefaultMessage()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorMessage inputParameterValidationException(ConstraintViolationException exception) {
        log.error("inputParameterValidationException:error -> {}", exception.getMessage());
        return new ApiErrorMessage(
                LocalDateTime.now(),
                "Validation Error for input parameter",
                exception.getMessage()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorMessage inputParameterNameException(IllegalArgumentException exception) {
        log.error("inputParameterNameException:error -> {}", exception.getMessage());
        return new ApiErrorMessage(
                LocalDateTime.now(),
                "Wrong input parameter name",
                exception.getMessage()
        );
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiErrorMessage handleOtherExceptions(Exception exception) {
//        log.error("handleOtherExceptions:error -> {}", exception.getMessage());
//        return new ApiErrorMessage(
//                LocalDateTime.now(),
//                "Internal Server Error Occurred",
//                exception.getMessage());
//    }
}
