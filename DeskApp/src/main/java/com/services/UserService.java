package com.services;

import java.util.List;

import com.models.User;

public interface UserService {

	public User save(User user);
	
	public User update(User user);
	
	public User getUserByUserName(String userName);
	
	public boolean isLoggedIn(User user);
	
	public List<User> getUsers();
}
