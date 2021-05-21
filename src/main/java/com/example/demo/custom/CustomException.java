package com.example.demo.custom;

/**
 * Handle the exceptions using a RestControllerAdvice
 */
public class CustomException extends RuntimeException{

    public CustomException(String msg) {
        super(msg);
    }
}
