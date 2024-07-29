package com.devsuperior.movie.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.movie.services.exceptions.DataBaseException;
import com.devsuperior.movie.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ValidatorError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		
		ValidatorError err = new ValidatorError();
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		err.setTimeStamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.ok().body(err);
	}

@ExceptionHandler(DataBaseException.class)
public ResponseEntity<ValidatorError> dataBase(DataBaseException e, HttpServletRequest request){
	
	ValidatorError err = new ValidatorError();
	
	HttpStatus status = HttpStatus.BAD_REQUEST;
	
	err.setTimeStamp(Instant.now());
	err.setStatus(status.value());
	err.setError("Data base exception");
	err.setMessage(e.getMessage());
	err.setPath(request.getRequestURI());
	
	return ResponseEntity.ok().body(err);
}
}
