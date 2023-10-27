package com.reldyn.springsecuritytoken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reldyn.springsecuritytoken.dto.RefreshTokenRequestDto;
import com.reldyn.springsecuritytoken.dto.SignInRequestDto;
import com.reldyn.springsecuritytoken.dto.SignUpDto;
import com.reldyn.springsecuritytoken.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthenticationService service;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody SignUpDto dto) {
		return ResponseEntity.ok(service.saveUser(dto));
	}
	
	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody  SignInRequestDto requestDto) {
		return ResponseEntity.ok(service.signIn(requestDto));
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody  RefreshTokenRequestDto requestDto) {
		return ResponseEntity.ok(service.reuestRefreshToken(requestDto));
	}
}
