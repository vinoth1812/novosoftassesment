package com.example.novosoft.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.NOT_FOUND.value(),
	                ex.getMessage(),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

	    /**
	     * Handle validation errors.
	     */
//	    @ExceptionHandler(ConstraintViolationException.class)
//	    public ResponseEntity<ErrorResponse> handleValidation(ConstraintViolationException ex) {
//	        ErrorResponse error = new ErrorResponse(
//	                HttpStatus.BAD_REQUEST.value(),
//	                ex.getMessage(),
//	                LocalDateTime.now()
//	        );
//	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	    }

	    /**
	     * Handle type mismatch errors.
	     */
//	    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//	    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
//	        String message = String.format("Invalid value '%s' for parameter '%s'.", ex.getValue(), ex.getName());
//	        ErrorResponse error = new ErrorResponse(
//	                HttpStatus.BAD_REQUEST.value(),
//	                message,
//	                LocalDateTime.now()
//	        );
//	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	    }

	    /**
	     * Handle generic exceptions.
	     */
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
	        ErrorResponse error = new ErrorResponse(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                "An unexpected error occurred.",
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


