package com.sothearithcompany.spring_homework_restapi2.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
