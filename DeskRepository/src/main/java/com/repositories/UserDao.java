package com.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.models.User;

public interface UserDao extends PagingAndSortingRepository<User, String> {

	public	User findUserByUserName(String userName);
	public  User findUserByEmail(String email); 
}
