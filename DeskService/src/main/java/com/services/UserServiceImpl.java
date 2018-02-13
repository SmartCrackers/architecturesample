package com.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.exception.BookException;
import com.modelUtility.EditableInfo;
import com.models.User;
import com.repositories.UserLogRepository;
import com.repositories.UserRepository;

import lombok.patcher.scripts.ScriptBuilder.ExitEarlyBuilder;

/**
 * @author RITESH SINGH
 *
 */
@Service("userService")
@ComponentScan("com.repositories")
public class UserServiceImpl implements UserService {
	
	final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepository userLogRepository;
	
	@Override
	public User save(User user) {
		
		validateUserForSignup(user);
		return userRepository.save(user);
	}
	
	private void validateUserForSignup(User user){
		try{
			
			if(user==null){
				LOGGER.error("User can not be blank.");
				String message = String.format("User can not be blank.");
				throw new BookException(message);
			}
			if(user.getUserName()==null){
				LOGGER.error("UserName can not be blank.");
				String message = String.format("UserName can not be blank.");
				throw new BookException(message);
			}
			if(user.getEmail()==null){
				LOGGER.error("UserEmail can not be blank.");
				String message = String.format("UserEmail can not be blank.");
				throw new BookException(message);
			}
			/*if(user.getPassword()==null){
				String message = String.format("UserPassword can not be blank.");
				throw new BookException(message);
			}*/
			
			User existingUserName = userRepository.getUserByUserName(user.getUserName());
			if(existingUserName==null){
				User existingUserEmail = userRepository.getUserByEmail(user.getEmail());
				if(existingUserEmail!=null){
					LOGGER.error("UserEmail already exist.");
					String message = String.format("UserEmail already exist.");
					throw new BookException(message);
				}
			}else{
				LOGGER.error("UserName already exist.");
				String message = String.format("UserName already exist.");
				throw new BookException(message);
			}
			
			EditableInfo editableInfo = null;
			editableInfo = new EditableInfo();
			editableInfo.setCreatedBy("userName");
			editableInfo.setUpdatedBy("username");
			
			user.setEditableInfo(editableInfo);
			
		}catch(Exception e){
			LOGGER.error("Error while validating User for create.");
			String message = String.format("Error while validating User for create.");
			throw new BookException(message, e);
		}
	}
	
	@Override
	public User update(User user) {
		
		User updatableUser =validateUserForUpdate(user);
		return userRepository.save(updatableUser);
	}
	
	private User validateUserForUpdate(User user){
		try{
			
			if(user==null){
				String message = String.format("User can not be blank.");
				throw new BookException(message);
			}
			
			if(user.getUserName()==null){
				String message = String.format("UserName can not be blank.");
				throw new BookException(message);
			}
			User existingUser = userRepository.getUserByUserName(user.getUserName());
			
			if(existingUser == null){
				String message = String.format("User is not found in db.");
				throw new BookException(message);
			}
			
			if(!user.getId().equals(existingUser.getId())){
				String message = String.format("User is not found in db.");
				throw new BookException(message);
			}
			
			if(!StringUtils.isEmpty(user.getAddress())){
				existingUser.setAddress(user.getAddress());
			}
			
			if(!StringUtils.isEmpty(user.getCompanyName())){
				existingUser.setCompanyName(user.getCompanyName());
			}
			if(!StringUtils.isEmpty(user.getDesignation())){
				existingUser.setDesignation(user.getDesignation());
			}
			if(!StringUtils.isEmpty(user.getEmail())){
				existingUser.setEmail(user.getEmail());
			}
			if(!StringUtils.isEmpty(user.getFirstName())){
				existingUser.setFirstName(user.getFirstName());
			}
			existingUser.setHandledProjects(user.getHandledProjects());
			
			if(!StringUtils.isEmpty(user.getHashedUserImage())){
				existingUser.setHashedUserImage(user.getHashedUserImage());
			}
			if(!StringUtils.isEmpty(user.getLastName())){
				existingUser.setLastName(user.getLastName());
			}
			existingUser.setMob(user.getMob());
			
			if(!StringUtils.isEmpty(user.getPassword())){
				existingUser.setPassword(user.getPassword());
			}
			existingUser.setPincode(user.getPincode());
			
			if(!StringUtils.isEmpty(user.getRecoveryEmail())){
				existingUser.setRecoveryEmail(user.getRecoveryEmail());
			}
			if(!StringUtils.isEmpty(user.getState())){
				existingUser.setState(user.getState());
			}
			if(!StringUtils.isEmpty(user.getAbout())){
				existingUser.setAbout(user.getAbout());
			}
			
			EditableInfo editableInfo = existingUser.getEditableInfo();
			editableInfo.setUpdatedBy("username");
			editableInfo.setUpdatedAt();
			existingUser.setEditableInfo(editableInfo);
			
			return existingUser;
			
		}catch(Exception e){
			LOGGER.error("Error while validating User for create.");
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
