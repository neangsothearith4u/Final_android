package com.sothearithcompany.spring_homework_restapi2.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String message){
        super(message);
    }
}
