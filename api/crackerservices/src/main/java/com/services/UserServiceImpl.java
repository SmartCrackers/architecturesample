package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.modelUtility.EditableInfo;
import com.models.User;

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
	
	@Override
	public User saveUser(User user) {
		
		if(user != null){
			
			User existedUser = userRepository.findOne(user.getId());
			EditableInfo editableInfo = null;
			
			/*Create a new user*/
			if(existedUser == null ){
				long count = userRepository.count();
				user.setId(count+1);
				editableInfo = new EditableInfo();
				editableInfo.setCreatedAt();
				editableInfo.setUpdatedAt();
				editableInfo.setCreatedBy("userName");
				editableInfo.setUpdatedBy("username");
			}else{
				/*Update An existed User*/
				editableInfo = existedUser.getEditableInfo();
				if(editableInfo!=null){
					editableInfo.setUpdatedAt();
					editableInfo.setUpdatedBy("username");
				}
			}
			
			user.setEditableInfo(editableInfo);
			return userRepository.save(user);
		}else
			return null;
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
	public List<User> getUsers(Sort sort) {
		
		return userRepository.findAll(sort);
	}

	@Override
	public User getUserById(Long userId) {
		
		return userRepository.findOne(userId);
	}

	@Override
	public User getUserByUserName(String userName) {
		
		return userRepository.getUserByUserName(userName);
	}
}
