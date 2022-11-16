package com.revature.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import com.revature.security.filter.AuthenticationFilter;
import com.revature.security.filter.ExceptionHandlerFilter;
import com.revature.security.filter.JWTAuthorizationFilter;




@Configuration
public class SecurityConfig {
	
	private CustomAuthenticationManager customAuthenticationManager;
	
	@Autowired
	public SecurityConfig(CustomAuthenticationManager customAuthenticationManager) {
		super();
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
            .antMatchers(HttpMethod.POST, "/reimbursements/updatereimbursement").hasAuthority("EMPLOYEE")
            .and()
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
        
        
        
    }



}
