package com.sothearithcompany.spring_homework_restapi2.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message){
        super(message);
    }
}
