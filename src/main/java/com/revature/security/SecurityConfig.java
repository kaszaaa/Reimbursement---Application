package com.revature.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.revature.security.filter.AuthenticationFilter;
import com.revature.security.filter.ExceptionHandlerFilter;




@Configuration
public class SecurityConfig {
	
	private BCryptPasswordEncoder passwordEncoder;
	private CustomAuthenticationManager customAuthenticationManager;
	
	@Autowired
	public SecurityConfig(BCryptPasswordEncoder passwordEncoder,
			com.revature.security.CustomAuthenticationManager customAuthenticationManager) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.customAuthenticationManager = customAuthenticationManager;
	}


	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/users/authenticate");
        http
        	.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users/register").permitAll() 
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
          
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
        
        
        
    }



}
