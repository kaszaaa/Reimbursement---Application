package com.revature.models;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Role {
	
	@Id
	private int roleID;
	@Column(nullable=false)
	private String roleName;
	@OneToMany(mappedBy="role", fetch=FetchType.LAZY)
	@JsonManagedReference
    private List<User> users = new ArrayList<>();
	
	public Role() {
		super();
	}

	public Role(int roleID, String roleName, List<User> users) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
		this.users = users;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleID, roleName, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return roleID == other.roleID && Objects.equals(roleName, other.roleName) && Objects.equals(users, other.users);
	}

	@Override
	public String toString() {
		return "Role [roleID=" + roleID + ", roleName=" + roleName + ", users=" + users + "]";
	}

	
	
}
