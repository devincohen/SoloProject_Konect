package com.codingdojo.soloprojectkonect.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name="HouseMember")
public class HouseMember {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message="First Name is required!")
	@Size (min=2, max=30, message="First Name must be between 2 and 30 characters.")
	private String fname;
	
	@NotBlank (message="Last Name is required!")
	@Size (min=2, max=30, message="Last Name must be between 2 and 30 characters.")
	private String lname;
	
	@NotNull (message="Birthday is required! Format MM/DD/YYYY")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	
	//created and updated times
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    //one-to-many, one household has many members
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "household_id")
    private Household household;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User memberCreator;
	
    @PrePersist
	 protected void onCreate() {
	 		this.createdAt = new Date();
	 }
	 @PreUpdate
	 protected void onUpdate() {
	 	this.updatedAt = new Date();
	 }
	
	public HouseMember() {}
	
	public HouseMember(String fname, String lname, Date birthday) {
		this.fname = fname;
		this.lname = lname;
		this.birthday = birthday;
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
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * @return the household
	 */
	public Household getHousehold() {
		return household;
	}
	/**
	 * @param household the household to set
	 */
	public void setHousehold(Household household) {
		this.household = household;
	}
	/**
	 * @return the memberCreator
	 */
	public User getMemberCreator() {
		return memberCreator;
	}
	/**
	 * @param memberCreator the memberCreator to set
	 */
	public void setMemberCreator(User memberCreator) {
		this.memberCreator = memberCreator;
	}
	
	
	
}
