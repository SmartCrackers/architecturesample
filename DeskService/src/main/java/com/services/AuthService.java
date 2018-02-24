package com.services;

import com.models.User;
import com.models.UserLog;

public interface AuthService {

	UserLog login(User userCredential);
	Boolean loggedOut(String token);
}
