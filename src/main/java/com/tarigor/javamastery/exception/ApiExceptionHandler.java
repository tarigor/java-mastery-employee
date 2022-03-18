package com.tarigor.javamastery.exception;

import com.tarigor.javamastery.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage employeeNotFoundException(EmployeeNotFoundException exception){
        return new ErrorMessage(
                HttpStatus.NOT_FOUND,
                new Date(),
                exception.getMessage(),
                "an employee has been not found with a such id"
        );
    }

    @ExceptionHandler(ErrorWhileAddEmployeeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage ErrorWhileAddEmployeeException(ErrorWhileAddEmployeeException exception){
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                new Date(),
                exception.getMessage(),
                "an error appeared while employee add"
        );
    }
}
