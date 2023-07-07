package com.sothearithcompany.spring_homework_restapi2.exception;

public class NonAuthoritativeInformationException extends RuntimeException{
    public NonAuthoritativeInformationException(String message){
        super(message);
    }
}
