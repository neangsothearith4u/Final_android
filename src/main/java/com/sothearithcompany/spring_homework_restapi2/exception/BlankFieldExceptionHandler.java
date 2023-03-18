package com.sothearithcompany.spring_homework_restapi2.exception;

public class BlankFieldExceptionHandler extends RuntimeException{
    public BlankFieldExceptionHandler(String message) {
        super(message);
    }
}
