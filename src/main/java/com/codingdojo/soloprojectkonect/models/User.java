package com.codingdojo.soloprojectkonect.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name="users")
public class User {
	
	//id of user
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	//first name
	@NotBlank (message="First Name is required!")
	@Size(min=2, max=30, message="First Name must be between 2 and 30 characters.")
	private String fname;
	
	//last name
	@NotBlank (message="Last Name is required!")
	@Size(min=2, max=30, message="Last Name must be between 2 and 30 characters.")
	private String lname;
	
	//email
	@NotBlank (message="Email is required!")
	@Email (message="Must be a valid email")
	private String email;
	
	//password
	@NotBlank (message="Password is required!")
	@Size (min=8, max=128, message="Password must be between 8 and 128 characters")
	private String password;
	
	
	//password confirmation stored in transient field
	@Transient
	@NotBlank(message="Confirmation is required")
	@Size(min=8, max=128, message="Confirm must be between 8 and 128 characters")
	private String confirm;
	
	//created and updated times
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    //one-to-many, one user can create many households
    @OneToMany (mappedBy = "houseCreator", fetch=FetchType.LAZY)
    private List<Household> households;
    
    @OneToMany (mappedBy = "memberCreator", fetch=FetchType.LAZY)
    private List<HouseMember> houseMembers;
    
    //many-to-many, one user can be part of many organizations, and one organization can have many users -- STRETCH
    
    //one-to-many, one user can own many organizations, but an organization can only have one owner -- STRETCH
    
    
    //many-to-many, one user can admin many organizations, and one organization can have many admins --STRETCH
    
    
    
    @PrePersist
	 protected void onCreate() {
	 		this.createdAt = new Date();
	 }
	 @PreUpdate
	 protected void onUpdate() {
	 	this.updatedAt = new Date();
	 }
	 
	 public User() {}
	 
	 public User(String fname, String lname, String email, String password) {
		 this.fname = fname;
		 this.lname = lname;
		 this.email = email;
		 this.password = password;
	 }
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the confirm
	 */
	public String getConfirm() {
		return confirm;
	}
	/**
	 * @param confirm the confirm to set
	 */
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @return the households
	 */
	public List<Household> getHouseholds() {
		return households;
	}
	/**
	 * @param households the households to set
	 */
	public void setHouseholds(List<Household> households) {
		this.households = households;
	}
	 
	 
	 
	 
	 
}
