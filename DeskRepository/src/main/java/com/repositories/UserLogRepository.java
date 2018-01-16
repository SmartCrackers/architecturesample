package com.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.models.UserLog;

/**
 * @author RITESH SINGH
 *
 */
public interface UserLogRepository extends MongoRepository<UserLog, String> {

	public	UserLog findUserLogByUserName(String userName);
	public	UserLog findUserLogByEmail(String email);
	public	UserLog findUserLogByToken(String token);
	public	UserLog findUserLogByTokenAndUserName(String token, String userName);
	public	UserLog findUserLogByTokenAndEmail(String token, String email);
	public	UserLog findUserLogByUserNameAndEmail(String userName,String email);
	public	UserLog findUserLogByUserNameAndEmailAndIsExpired(String userName,String email,Boolean isExpired);
}
