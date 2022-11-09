package com.revature.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.requests.CreateUserRequest;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {
	
private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@Bean 
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody CreateUserRequest req) {
		userService.register(req);
		return ResponseEntity.status(200).build();
	}
	
//	@PostMapping
//	public ResponseEntity<User> loginAttempt(@RequestBody User user, HttpSession session){
//		user = userService.login(user);
//		if(user != null) {
//			session.setAttribute("logged in", true);
//			session.setAttribute("user", user);
//			return ResponseEntity.status(200).body(user);
//		}else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//	
//	}	
	
	@GetMapping("/{id}")
	public UserDTO getByID(@PathVariable int id){
		final User user= userService.getByUserId(id);
		return new UserDTO(user);
	}

} 
