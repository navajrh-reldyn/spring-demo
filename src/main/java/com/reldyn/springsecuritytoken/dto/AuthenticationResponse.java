package com.reldyn.springsecuritytoken.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

	private String previousToken;
	private String refreshToken;
}
