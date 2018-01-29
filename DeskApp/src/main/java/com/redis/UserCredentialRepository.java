package com.redis;

import java.util.Map;

public interface UserCredentialRepository {

	void save(UserCredential userCredential);
	public void update(UserCredential userCredential);
	public UserCredential findByToken(String token);
	public Map<Object, Object> findAllTokens();
	void deleteStudent(String token);
}
