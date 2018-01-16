package com.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.models.UserLog;

public interface UserLogDao extends PagingAndSortingRepository<UserLog, String> {

	public	UserLog findUserLogByUserName(String userName);
	public	UserLog findUserLogByEmail(String email);
	public	UserLog findUserLogByToken(String token);
	public	UserLog findUserLogByUserNameAndEmail(String userName,String email);
	public	UserLog findUserLogByUserNameAndEmailAndIsExpired(String userName,String email,Boolean isExpired);
	public	UserLog findUserLogByTokenAndUserName(String token, String userName);
	public	UserLog findUserLogByTokenAndEmail(String token, String email);
}
