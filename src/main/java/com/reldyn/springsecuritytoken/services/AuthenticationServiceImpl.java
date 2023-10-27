package com.reldyn.springsecuritytoken.services;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reldyn.springsecuritytoken.dto.AuthenticationResponse;
import com.reldyn.springsecuritytoken.dto.RefreshTokenRequestDto;
import com.reldyn.springsecuritytoken.dto.SignInRequestDto;
import com.reldyn.springsecuritytoken.dto.SignUpDto;
import com.reldyn.springsecuritytoken.entities.Role;
import com.reldyn.springsecuritytoken.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	private final UserServiceImpl serviceImpl;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager manager;
	private final JWTService jwtService;

	public static User user;
	

	@Override
	public User saveUser(SignUpDto dto) {
		user = User.builder().email(dto.getEmail()).firstName(dto.getFirstName()).lastName(dto.getLastName())
				.password(dto.getPassword()).role(Role.USER).build();

//		return serviceImpl.getUserDetails().stream().map(x -> {
//			x.setPassword(passwordEncoder.encode(dto.getPassword()));
//			return x;
//		}).toList().get(0);
		System.out.println(user);
		return user;

	}

	@Override
	public AuthenticationResponse signIn(SignInRequestDto requestDto) {

		System.out.println(user);
//		User user = serviceImpl.getUserDetails().stream().filter(x -> x.getEmail().equals(requestDto.getEmail()))
//				.findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
//		if (!requestDto.getEmail().equals(user.getEmail()) || !requestDto.getPassword().equals(user.getPassword())) {
//			throw new IllegalArgumentException("Invalid email or password");
//		} else {
			Authentication authenticate = manager.authenticate(
					new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));
//		}
		var genarateToken = jwtService.genarateToken(getUser());
		var refreshToken = jwtService.genarateRefreshToken(new HashMap<>(), user);
		return AuthenticationResponse.builder().previousToken(genarateToken).refreshToken(refreshToken).build();

	}

	@Override
	public AuthenticationResponse reuestRefreshToken(RefreshTokenRequestDto requestDto) {
		String userName = jwtService.getUserName(requestDto.getToken());
		Map<String, User> collect = serviceImpl.getUserDetails().stream()
				.collect(Collectors.toMap(User::getEmail, user -> user));
		if (jwtService.isTokenValid(requestDto.getToken(), collect.get(userName))) {
			String genarateToken = jwtService.genarateToken(collect.get(userName));

			return AuthenticationResponse.builder().previousToken(requestDto.getToken()).refreshToken(genarateToken)
					.build();
		}
		return null;
	}
	
	public User getUser() {
		return User.builder().email(user.getEmail()).firstName(user.getFirstName()).lastName(user.getLastName())
				.password(passwordEncoder.encode(user.getPassword())).role(Role.USER).build();

	}
}
