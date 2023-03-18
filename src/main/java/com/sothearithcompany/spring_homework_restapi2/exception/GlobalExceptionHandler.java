package com.sothearithcompany.spring_homework_restapi2.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//@ControllerAdvice

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle exception Not found
    @ExceptionHandler(NotFoundException.class)
    ProblemDetail handleNotFoundException(NotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setProperty("time", LocalDateTime.now());

        return problemDetail;
    }

    // Handle black field

    @ExceptionHandler(BlankFieldExceptionHandler.class)
    ProblemDetail handleBlankFieldException(BlankFieldExceptionHandler e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
        problemDetail.setProperty("time", LocalDateTime.now());
        return problemDetail;
    }

}
