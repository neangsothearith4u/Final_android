package com.sothearithcompany.spring_homework_restapi2.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message){
        super(message);
    }
}
