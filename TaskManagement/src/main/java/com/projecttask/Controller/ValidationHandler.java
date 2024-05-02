package com.projecttask.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidation(MethodArgumentNotValidException e){
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(er -> {
			FieldError fe = (FieldError)er;
			errors.put(fe.getField(), fe.getDefaultMessage());
		});
		return errors;
	}
}
