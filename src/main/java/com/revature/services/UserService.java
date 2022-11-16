package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.requests.CreateUserRequest;


@Service
public class UserService {
	private UserDAO userDAO;
	private RoleService roleService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Autowired
	public UserService(UserDAO userDAO, RoleService roleService, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDAO = userDAO;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void register(CreateUserRequest req) {
		final User newUser = new User();
			newUser.setUsername(req.getUsername());
			newUser.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));
			newUser.setFirstname(req.getFirstname());
			newUser.setLastname(req.getLastname());
			newUser.setEmail(req.getEmail());
			newUser.setRole(roleService.getByRoleID(req.getRoleID()));
			userDAO.save(newUser);
		
	}
	
	public User getUser(String username) {
        return userDAO.getUserByUsername(username).orElse(null);
	}
	
	public User getByUserId(int id) {
		return userDAO.getByUserID(id).orElse(null);
		
	}

}
