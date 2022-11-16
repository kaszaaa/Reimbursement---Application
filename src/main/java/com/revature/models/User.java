package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userID;
	@OneToMany(mappedBy="author", fetch=FetchType.LAZY)
	@JsonManagedReference(value="author-reimbursement")
	//@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "@id")
    private List<Reimbursement> reimbursementsAuthor = new ArrayList<>();
	@OneToMany(mappedBy="resolver", fetch=FetchType.LAZY)
	@JsonManagedReference(value="resolver-reimbursement")
	//@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "@userid")
    private List<Reimbursement> reimbursementsResolver = new ArrayList<>();
	@Column(nullable=false, unique=true)
    private String username;
	@Column(nullable=false)
    private String password;
    private String firstname;
    private String lastname;
    @Column(nullable=false, unique=true)
    private String email;
    //private int roleID;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="roleID")
	@JsonBackReference
    private Role role;
    
	public User() {
		super();
	}

	public User(int userID, List<Reimbursement> reimbursementsAuthor, List<Reimbursement> reimbursementsResolver,
			String username, String password, String firstname, String lastname, String email, Role role) {
		super();
		this.userID = userID;
		this.reimbursementsAuthor = reimbursementsAuthor;
		this.reimbursementsResolver = reimbursementsResolver;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public List<Reimbursement> getReimbursementsAuthor() {
		return reimbursementsAuthor;
	}

	public void setReimbursementsAuthor(List<Reimbursement> reimbursementsAuthor) {
		this.reimbursementsAuthor = reimbursementsAuthor;
	}

	public List<Reimbursement> getReimbursementsResolver() {
		return reimbursementsResolver;
	}

	public void setReimbursementsResolver(List<Reimbursement> reimbursementsResolver) {
		this.reimbursementsResolver = reimbursementsResolver;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstname, lastname, password, reimbursementsAuthor, reimbursementsResolver, role,
				userID, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(password, other.password)
				&& Objects.equals(reimbursementsAuthor, other.reimbursementsAuthor)
				&& Objects.equals(reimbursementsResolver, other.reimbursementsResolver)
				&& Objects.equals(role, other.role) && userID == other.userID
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", reimbursementsAuthor=" + reimbursementsAuthor + ", reimbursementsResolver="
				+ reimbursementsResolver + ", username=" + username + ", password=" + password + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", role=" + role + "]";
	}

	
	
}
	