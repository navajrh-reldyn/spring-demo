package com.reldyn.springsecuritytoken.services;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService {

	private String secretkey = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkFkbWluIiwiZXhwIjoxNjk3OTYwNjE0LCJpYXQiOjE2OTc5NjA2MTR9.vpGhItFv_xqfEhIk5g6aGuxexO1LaRWOnroLh2dunA8";

	@Override
	public String genarateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))
				.signWith(getSignkey(), SignatureAlgorithm.HS256).compact();
	}

	@Override
	public String genarateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignkey(), SignatureAlgorithm.HS256).compact();
	}

	// get sign in key
	private Key getSignkey() {
		byte[] key = Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(key);
	}

	// to extract username from claim first get all claims
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignkey()).build().parseClaimsJws(token).getBody();
	}

	// get Username from token
	@Override
	public String getUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// get current claim
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	@Override
	// to validate token
	public boolean isTokenValid(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		final String userName = getUserName(token);
		return (userName.equals(userDetails.getUsername()) && isTokenVaid(token));
	}

	// whether token expired
	private boolean isTokenVaid(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
}
