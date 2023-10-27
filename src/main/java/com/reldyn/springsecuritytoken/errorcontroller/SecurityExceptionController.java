package com.reldyn.springsecuritytoken.errorcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class SecurityExceptionController {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(@RequestBody Exception exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
}
