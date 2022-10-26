package com.revature.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.requests.CreateUserRequest;

@Service
public class UserService {
	private UserDAO userDAO;
	private RoleService roleService;

	
	@Autowired
	public UserService(UserDAO userDAO, RoleService roleService) {
		super();
		this.userDAO = userDAO;
		this.roleService = roleService;
	}
	
	public void register(CreateUserRequest req) {
		final User newUser = new User();
			newUser.setUsername(req.getUsername());
			newUser.setPassword(req.getPassword());
			newUser.setFirstname(req.getFirstname());
			newUser.setLastname(req.getLastname());
			newUser.setEmail(req.getEmail());
			newUser.setRole(roleService.getByRoleID(req.getRoleID()));
			userDAO.save(newUser);
		
	}

	public User getUser(int id) {
		return userDAO.getByUserID(id).orElse(null);
		
	}
	public User login(User user) {
		Optional<User> dbUser = userDAO.getUserByUsername(user.getUsername());
		if(dbUser.isPresent()) {
			return dbUser.get();
		}
		return null;
	}

}
