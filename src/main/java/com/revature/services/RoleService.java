package com.revature.services;

import org.springframework.stereotype.Service;
import com.revature.models.Role;
import com.revature.repositories.RoleDAO;


@Service
public class RoleService{
	private RoleDAO roleDAO;
	
	public RoleService(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public Role getByRoleID(int roleID) {
		return roleDAO.getByRoleID(roleID);
//		Role newRole = new Role();
//		newRole.setRoleID(roleID);
//		if(roleID==1) {
//			newRole.setRoleName("employee");
//		}else if(roleID==2) {
//			newRole.setRoleName("finance_manager");
//		}
//		roleDAO.save(newRole);
//		return newRole;
	}

}