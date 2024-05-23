package com.codingdojo.soloprojectkonect.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.soloprojectkonect.models.Household;
import com.codingdojo.soloprojectkonect.repositories.HouseholdRepository;


@Service
public class HouseholdService {
	@Autowired
	private HouseholdRepository houseRepo;
	
	//make new household
	public Household createHouse(Household h) {
		return houseRepo.save(h);
	}
	
	public List<Household> allHouseholds(){
		return houseRepo.findAll();
	}
	
	
	//find household by id
	public Household findHouseById(Long id) {
		Optional<Household> potentialHouse = houseRepo.findById(id);
		if(potentialHouse.isPresent()) {
			return potentialHouse.get();
		}
		return null;
	}
	
	
	//update household
	
	public Household updateHousehold(Household h) {
		return houseRepo.save(h);
	}
	
	
	//delete household
	public void deleteHousehold (Household h) {
		houseRepo.delete(h);
	}

}
