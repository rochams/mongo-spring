package com.masaro.springmongo.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GetExceptionHandle {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<DefaultError> notFound(NotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		DefaultError defaultError = new DefaultError(
				System.currentTimeMillis(), 
				status.value(), 
				"Object Not Found.", 
				e.getMessage(), 
				request.getRequestURI());
		return ResponseEntity.status(status).body(defaultError);
	}

}
