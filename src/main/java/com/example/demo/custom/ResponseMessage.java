package com.example.demo.custom;


import lombok.Data;

@Data
public class ResponseMessage {
    private String message;

    public ResponseMessage(String msg){
        this.message = msg;
    }
}
