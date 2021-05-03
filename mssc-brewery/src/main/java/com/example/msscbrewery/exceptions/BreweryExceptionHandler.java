package com.example.msscbrewery.exceptions;

import com.example.msscbrewery.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class BreweryExceptionHandler {

    @ExceptionHandler(BreweryException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(BreweryException e) {
        String[] errors = e.getMessage().split(",");
        return new ResponseEntity<>(ErrorMessage.builder()
                .messages(Arrays.asList(errors))
                .time(LocalDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception e) {
        String[] errors = e.getMessage().split(",");
        return new ResponseEntity<>(ErrorMessage.builder()
                .messages(Arrays.asList(errors))
                .time(LocalDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST);
    }
}
