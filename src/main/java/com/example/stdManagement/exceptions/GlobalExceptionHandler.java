package com.example.stdManagement.exceptions;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<?> handleInvalidUsernameException(InvalidUsernameException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handleInvalidPasswordException(InvalidPasswordException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
    	System.out.println(request);
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}




