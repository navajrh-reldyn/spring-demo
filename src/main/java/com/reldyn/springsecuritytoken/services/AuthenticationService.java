package com.reldyn.springsecuritytoken.services;

import com.reldyn.springsecuritytoken.dto.AuthenticationResponse;
import com.reldyn.springsecuritytoken.dto.RefreshTokenRequestDto;
import com.reldyn.springsecuritytoken.dto.SignInRequestDto;
import com.reldyn.springsecuritytoken.dto.SignUpDto;
import com.reldyn.springsecuritytoken.entities.User;

public interface AuthenticationService {

	User saveUser(SignUpDto dto);
	
	AuthenticationResponse signIn(SignInRequestDto requestDto);

	AuthenticationResponse reuestRefreshToken(RefreshTokenRequestDto requestDto);
}
