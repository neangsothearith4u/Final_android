package com.sothearithcompany.spring_homework_restapi2.exception;

public class NotModifiedException extends RuntimeException{
    public NotModifiedException(String message){
        super(message);
    }
}
