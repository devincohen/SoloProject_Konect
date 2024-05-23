package com.codingdojo.soloprojectkonect.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.soloprojectkonect.models.Household;

public interface HouseholdRepository extends CrudRepository<Household, Long> {

	//find all households
	List<Household> findAll(); 
	
	//find by id
	Optional<Household> findById(Long id);
}
