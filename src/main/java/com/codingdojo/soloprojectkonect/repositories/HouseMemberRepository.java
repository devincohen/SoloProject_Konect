package com.codingdojo.soloprojectkonect.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.soloprojectkonect.models.HouseMember;

public interface HouseMemberRepository extends CrudRepository<HouseMember, Long> {

	//find all HouseMembers
	List<HouseMember> findAll();
}
