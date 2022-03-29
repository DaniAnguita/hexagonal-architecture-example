package com.company.demo.exception.api;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.company.demo.common.model.ExceptionResponse;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(getResponse(exception));
    }
    
    private ExceptionResponse getResponse(MethodArgumentNotValidException exception) {
    	return new ExceptionResponse(getErrors(exception));
    }
    
    private Map<String, String> getErrors(MethodArgumentNotValidException exception) {
    	return exception.getBindingResult()
            	.getFieldErrors()
            	.stream()
            	.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

}
