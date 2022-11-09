package com.revature.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.security.CustomAuthenticationManager;



public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private CustomAuthenticationManager authenticationManager;
	
	@Autowired
	public AuthenticationFilter(CustomAuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	



	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			return authenticationManager.authenticate(authentication);
		}catch(IOException e){
			throw new RuntimeException();
		}
		
	}

	

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.print("it does not work");
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.print("it works");
	}

	
	
}