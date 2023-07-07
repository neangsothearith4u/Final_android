package com.sothearithcompany.spring_homework_restapi2.exception;



public class FieldBlankExceptionHandler extends RuntimeException{
    public FieldBlankExceptionHandler(String message) {

        super(message);
    }
}
