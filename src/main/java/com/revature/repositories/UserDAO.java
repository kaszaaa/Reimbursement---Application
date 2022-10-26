package com.revature.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Role;
import com.revature.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
	public Optional<User> getUserByUsername(String username);
	public Optional<User> getByRole(Role role);
	public Optional<User> getByUserID(int userID);
}
