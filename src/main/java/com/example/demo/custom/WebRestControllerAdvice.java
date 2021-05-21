package com.example.demo.custom;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseMessage handleNotFoundException(CustomException ex) {
        return new ResponseMessage(ex.getMessage());
    }
}