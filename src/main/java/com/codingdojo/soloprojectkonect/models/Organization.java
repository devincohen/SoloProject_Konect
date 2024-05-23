package com.codingdojo.soloprojectkonect.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name="Organization")
public class Organization {
	//id
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	//name
	@NotBlank (message="Organization name is required!")
	private String name;
	
	//address
	@NotBlank (message="Street Address is required!")
	@Size (min=3, max = 255, message="Street Address must be between 3 and 255 characters")
	private String streetAddress;
	
	@NotBlank (message="City is required!")
	@Size (min=3, max = 255, message="City must be between 3 and 255 characters")
	private String city;
	
	@NotBlank (message="State is required!")
	@Size (min=2, message="State must be 2 letter abbreviatiokn")
	private String state;
	
	@NotBlank (message="Zip code is required!")
	@Size (min=5, message="Zip code must be 2 letter abbreviatiokn")
	private String zip;
	
	
	//created and updated times
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    
    
    @PrePersist
	 protected void onCreate() {
	 		this.createdAt = new Date();
	 }
	 @PreUpdate
	 protected void onUpdate() {
	 	this.updatedAt = new Date();
	 }
}
