package com.infy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.DTO.ErrorMessage;
@RestControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex)
	{
		return ex.getMessage();
	}
	
	@ExceptionHandler(WecareException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(WecareException we){
		ErrorMessage error = new ErrorMessage();
		error.setMessage(we.getMessage());
		return new ResponseEntity<>(error,HttpStatus.OK);
	}
	

}
