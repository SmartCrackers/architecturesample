package com.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.models.User;

/**
 * @author RITESH SINGH
 *
 */
public interface UserRepository extends MongoRepository<User, String> {

	public User getUserByUserName(String userName);
	public User getUserByEmail(String email);
}
