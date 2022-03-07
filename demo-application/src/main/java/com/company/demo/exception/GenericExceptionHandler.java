package com.company.demo.exception;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.company.demo.common.data.ExceptionResponse;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleBeanValidationException(ConstraintViolationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
            			.errors(getErrors(exception.getConstraintViolations()))
            			.build());
    }
    
	private Map<String, String> getErrors(Set<ConstraintViolation<?>> constraints) {
		return constraints.stream().collect(Collectors.toMap(
				c -> getFieldName(c), 
				c -> c.getMessage(), 
				(c1, c2) -> c1)
			);
    }
    
    private String getFieldName(ConstraintViolation<?> error) {
    	return getLastNode(error.getPropertyPath().iterator()).getName();
    }
    
    private Path.Node getLastNode(Iterator<Path.Node> it) {
    	Path.Node result = null;
    	while(it.hasNext()) {
    		result = it.next();
    	}
    	return result;
    }

}
