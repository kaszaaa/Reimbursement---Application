package com.revature.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.revature.models.User;
import com.revature.services.UserService;

@Component
public class CustomAuthenticationManager implements AuthenticationManager{

	private UserService userService;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomAuthenticationManager(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		User user = userService.getUser(authentication.getName());
		if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("You provided an incorrect password.");
        }
		return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
	}

	
    
	
}