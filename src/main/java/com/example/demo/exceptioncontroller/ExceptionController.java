package com.example.demo.exceptioncontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.NotNullException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = NotNullException.class)
	public ResponseEntity<?> handleNullPointerExcddeption(@RequestBody NotNullException exception) {
		return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
	}
}
