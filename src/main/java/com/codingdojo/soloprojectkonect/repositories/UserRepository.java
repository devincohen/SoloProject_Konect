package com.codingdojo.soloprojectkonect.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.soloprojectkonect.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	//find user by email
	Optional<User> findByEmail(String email);
}
