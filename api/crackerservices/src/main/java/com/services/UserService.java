package com.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.models.User;


/**
 * @author RITESH SINGH
 *
 */
public interface UserService {

	public User saveUser(User user);
	
	public Long count();
	
	public List<User> getUsers();
	
	public List<User> getUsers(Sort sort);
	
	public User getUserById(Long userId);
	
	public User getUserByUserName(String userName);
}
