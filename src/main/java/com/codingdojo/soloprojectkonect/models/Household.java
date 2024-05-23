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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name="households")
public class Household {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	//name
	@NotBlank
	@Size (min=3, max = 255, message="Name must be between 3 and 30 characters")
	private String name;
	
	//address
	@NotBlank (message="Street Address is required!")
	@Size (min=3, max = 255, message="Street Address must be between 3 and 255 characters")
	private String streetAddress;
	
	private String streetAddress2;
	
	@NotBlank (message="City is required!")
	@Size (min=3, max = 255, message="City must be between 3 and 255 characters")
	private String city;
	
	@NotBlank (message="State is required!")
	@Size (min=2, message="State must be 2 letter abbreviatiokn")
	private String state;
	
	@NotBlank (message="Zip code is required!")
	@Size (min=5, message="Zip code must be 2 letter abbreviatiokn")
	private String zip;
	
	@Size(max=30000)
	private String notes;
	
	//created and updated times
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    //one-to-many, one household has many members
    @OneToMany (mappedBy="household", fetch=FetchType.LAZY)
    private List<HouseMember> housemembers;
    
    //one-to-many, one user can create many households
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="user_creator_id")
    private User houseCreator;
    
    @PrePersist
	 protected void onCreate() {
	 		this.createdAt = new Date();
	 }
	 @PreUpdate
	 protected void onUpdate() {
	 	this.updatedAt = new Date();
	 }
	 
	public Household() {}
	
	public Household(String name, String streetAddress, String streetAddress2, String city, String state, String zip, String notes) {
		this.name = name;
		this.streetAddress = streetAddress;
		this.streetAddress2 = streetAddress2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}
	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	/**
	 * @return the streetAddress2
	 */
	public String getStreetAddress2() {
		return streetAddress2;
	}
	/**
	 * @param streetAddress2 the streetAddress2 to set
	 */
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
	 * @return the housemembers
	 */
	public List<HouseMember> getHousemembers() {
		return housemembers;
	}
	/**
	 * @param housemembers the housemembers to set
	 */
	public void setHousemembers(List<HouseMember> housemembers) {
		this.housemembers = housemembers;
	}
	/**
	 * @return the creator
	 */
	public User getHouseCreator() {
		return houseCreator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setHouseCreator(User creator) {
		this.houseCreator = creator;
	}
	
	
	
}
