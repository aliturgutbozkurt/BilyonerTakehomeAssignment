package com.bilyoner.microservices.homework.web;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bilyoner.microservices.homework.exception.NoSuchCustomDataWithNumberException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleException(DuplicateKeyException e) {
    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Duplicate record with this number");
    }  
    
    @ExceptionHandler(NoSuchCustomDataWithNumberException.class)
    public ResponseEntity<?> handleException(NoSuchCustomDataWithNumberException e) {
    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No recod with this number");
    }
    
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleException(NumberFormatException e) {
    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not a number");
    }
    
}