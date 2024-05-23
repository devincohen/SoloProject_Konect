package com.codingdojo.soloprojectkonect.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.soloprojectkonect.models.HouseMember;
import com.codingdojo.soloprojectkonect.models.Household;
import com.codingdojo.soloprojectkonect.repositories.HouseMemberRepository;
import com.codingdojo.soloprojectkonect.repositories.HouseholdRepository;

@Service
public class HouseMemberService {
	@Autowired
	private HouseMemberRepository memberRepo;
	@Autowired
	private HouseholdRepository houseRepo;
	

	
	//create new member
	public HouseMember createMember(HouseMember h) {
		return memberRepo.save(h);
	}
	
	
	//find all members
	public List<HouseMember> allMembers(){
		return memberRepo.findAll();
	}
	//find all members from one household
	public List<HouseMember> findMembersInHouseholdById(Long id){
		Optional<Household> household = houseRepo.findById(id);
		if(!household.isPresent()) {
			return null;
		}
		List<HouseMember> members = memberRepo.findAll();
		for(int i = members.size()-1; i>=0; i--) {
			if(members.get(i).getHousehold() != household.get()) {
				members.remove(i);
			}
		}
		return members;
	}
	
	
	//find member by id
	public HouseMember findMemberById(Long id) {
		Optional<HouseMember> potentialMember = memberRepo.findById(id);
		if (potentialMember.isPresent()) {
			return potentialMember.get();
		}
		return null;
	}
	
	//update member
	public HouseMember updateMember(HouseMember h) {
		return memberRepo.save(h);
	}
	
	
	//delete member
	public void deleteMember(HouseMember h) {
		memberRepo.delete(h);
	}
}
