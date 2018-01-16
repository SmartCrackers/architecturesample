package com.services;

import com.models.UserLog;

public interface AuthService {

	UserLog login(UserLog userCredential);
	Boolean loggedOut(String token);
}
