package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.exception.BookException;
import com.models.User;
import com.models.UserLog;
import com.repositories.UserLogRepository;
import com.repositories.UserRepository;

/**
 * @author RITESH SINGH
 */
@Service("authService")
@ComponentScan("com.repositories")
public class AuthServiceImpl implements AuthService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepository userLogRepository;
	
	@Override
	public UserLog login(User userCredential) {
		
		validateUserCredentialForLogin(userCredential);
		UserLog userLog = null;
		try{
			
			User user = userRepository.getUserByUserName(userCredential.getUserName());
			if(user!=null)
				if(userCredential.getPassword().equals(user.getPassword())){
					userLog = new UserLog(user);
					userLogRepository.save(userLog);
				}
			
			return userLog;
		}catch(Exception e){
			String message = String.format("Error while login user.");
			throw new BookException(message, e);
		}
	}
	
	private void validateUserCredentialForLogin(User userCredential){
		try{
			if(userCredential.getUserName()==null || userCredential.getPassword()==null)
				throw new BookException("Not Valid UserCredential");
		}catch (Exception e) {
			String message = String.format("Error while validate user credential for login.");
			throw new BookException(message, e);
		}
	}
	
	@Override
	public Boolean loggedOut(String token) {
		try{
			if(token != null){
				UserLog userLog = userLogRepository.findUserLogByToken(token);
				if(userLog!=null){
					userLog.setIsExpired(true);
					
					userLogRepository.save(userLog);
					return userLog.getIsExpired();
				}
			}
			return false;
		}catch(Exception e){
			String message = String.format("Error while logout user.");
			throw new BookException(message, e);
		}
	}
}
