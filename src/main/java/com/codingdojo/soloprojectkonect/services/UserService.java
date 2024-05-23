package com.codingdojo.soloprojectkonect.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.soloprojectkonect.models.LoginUser;
import com.codingdojo.soloprojectkonect.models.User;
import com.codingdojo.soloprojectkonect.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	//Login and Register
	public User register(User newUser, BindingResult result) {
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		if(potentialUser.isPresent()) {
			result.rejectValue("email", "Matched", "Email already in use!");
			return null;
		}
		
		if(!newUser.getPassword().equals(newUser.getConfirm())){
			result.rejectValue("password","Confirm failed","Password and Confirmation must match!");
		}
		
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return newUser;
	}
	
	public User login(LoginUser newLoginObject, BindingResult result) {
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		if(!potentialUser.isPresent()) {
			result.rejectValue("email","Does not exist","Invalid Email or Password");
			return null;
		}
		if(!BCrypt.checkpw(newLoginObject.getPassword(), potentialUser.get().getPassword())) {
			result.rejectValue("password","Does not match","Invalid Email or Password");
			return null;
		}
		return potentialUser.get();
		
	}
	
	//add user
	public User addUser(User u) {
		return userRepo.save(u);
	}
	
	//find user by id
	public User findUserById(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if(potentialUser.isPresent()) {
			return potentialUser.get();
		}
		return null;
	}
	
	//update user
	public User updateUser(User u) {
		return userRepo.save(u);
	}
	
	
}
