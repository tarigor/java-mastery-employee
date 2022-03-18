package com.tarigor.javamastery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private HttpStatus httpStatus;
    private Date timeStamp;
    private String message;
    private String description;
}
