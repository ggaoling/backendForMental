package com.example.demo.basic;

public class NotFoundException extends GlobalException {
    public NotFoundException(String message,int code){
        super(message,code);
    }
}
