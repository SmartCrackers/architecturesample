package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exception.BookException;
import com.modelUtility.EditableInfo;
import com.models.User;
import com.repositories.UserLogRepository;
import com.repositories.UserRepository;

/**
 * @author RITESH SINGH
 *
 */
@Service("userService")
@ComponentScan("com.repositories")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepository userLogRepository;
	
	@Override
	public User save(User user) {
		
		validateUser(user);
		return userRepository.save(user);
	}
	
	private void validateUser(User user){
		try{
			
			if(user==null){
				String message = String.format("User can not be blank.");
				throw new BookException(message);
			}
			if(user.getUserName()==null){
				String message = String.format("UserName can not be blank.");
				throw new BookException(message);
			}
			if(user.getEmail()==null){
				String message = String.format("UserEmail can not be blank.");
				throw new BookException(message);
			}
			if(user.getPassword()==null){
				String message = String.format("UserPassword can not be blank.");
				throw new BookException(message);
			}
			
			User existingUserName = userRepository.getUserByUserName(user.getUserName());
			if(existingUserName==null){
				User existingUserEmail = userRepository.getUserByEmail(user.getEmail());
				if(existingUserEmail!=null){
					String message = String.format("UserEmail already exist.");
					throw new BookException(message);
				}
			}else{
				String message = String.format("UserName already exist.");
				throw new BookException(message);
			}
			
			EditableInfo editableInfo = null;
			editableInfo = new EditableInfo();
			editableInfo.setCreatedBy("userName");
			editableInfo.setUpdatedBy("username");
			
			user.setEditableInfo(editableInfo);
			
		}catch(Exception e){
			String message = String.format("Error while validating User for create.");
			throw new BookException(message, e);
		}
	}

	@Override
	public Long count() {
		return userRepository.count();
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public Page<User> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public List<User> getUsers(Sort sort) {
		return userRepository.findAll(sort);
	}

	@Override
	public User getUserById(String userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userRepository.getUserByUserName(userName);
	}
}
