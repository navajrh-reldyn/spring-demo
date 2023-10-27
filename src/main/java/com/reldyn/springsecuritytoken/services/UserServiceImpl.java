package com.reldyn.springsecuritytoken.services;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reldyn.springsecuritytoken.entities.Role;
import com.reldyn.springsecuritytoken.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static User user;
	
//	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetailsService userDetailsService() {

		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				return getUserDetails().stream().filter(x -> x.getEmail().equals(username)).findFirst()
//						.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//				User user2 = AuthenticationServiceImpl.user;
//				user = User.builder().email(user2.getEmail()).firstName(user2.getFirstName()).lastName(user2.getLastName())
//						.password(passwordEncoder.encode(user2.getPassword())).role(Role.USER).build();

				return new AuthenticationServiceImpl(null, null, null, null).getUser();
			}
		};
	}

	public List<User> getUserDetails() {
		return IntStream.range(1, 10).mapToObj(x -> x).map(y -> User.builder().firstName("abc" + y).lastName("xyz" + y)
				.email("abcxyz@gmail" + y).password("12345xyz" + y).role(y % 2 == 0 ? Role.ADMIN : Role.USER).build())
				.toList();
	}

}
