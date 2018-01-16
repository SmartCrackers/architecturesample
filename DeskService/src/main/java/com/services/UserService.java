package com.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.models.User;


/**
 * @author RITESH SINGH
 *
 */
public interface UserService {

	User save(User user);
	
	Long count();
	
	List<User> getUsers(Sort sort);
	
	User getUserById(String userId);
	
	User getUserByUserName(String userName);

	Page<User> getUsers(Pageable pageable);
	
	List<User> getUsers();
}
