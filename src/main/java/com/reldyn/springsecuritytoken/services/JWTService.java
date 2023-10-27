package com.reldyn.springsecuritytoken.services;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

	String genarateToken(UserDetails details);
	
	String getUserName(String token);
	
	boolean isTokenValid(String token,UserDetails userDetails);

	String genarateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails);
}
