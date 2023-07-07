package com.sothearithcompany.spring_homework_restapi2.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
