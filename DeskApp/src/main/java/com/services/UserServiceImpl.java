package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.Constants;
import com.DeskAppWebException;
import com.Response;
import com.config.SessionConfig;
import com.dao.DataAccessObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.models.User;

@Service("userService")
public class UserServiceImpl extends DataAccessObject implements UserService {

	final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Value("${ip}")
	private String ip;
	
	@Value("${port}")
	private String port;
	
	@Value("${api}")
	private String api;
	
	@Autowired
	@Qualifier("fileUtilityServiceImpl")
	private FileUtilityService fileUtilityService;
	
	@Override
	public User save(User user) {
		Gson gson = new Gson();
		String url = ip+port+api;
		String data = new Gson().toJson(user);
		
		Map<String, String> header = this.createHeaderInstance();
		
		try{
			this.validateUser(user);
			Response<User> apiResponse = gson.fromJson(this.sendPOST(url+Constants.USER_SAVE_API, data, header),new TypeToken<Response<User>>(){}.getType());
			
			if(apiResponse.getStatus() != 200){
				fileUtilityService.deleteFile(user.getId(), "profile", user.getHashedUserImage());
				logger.error("uploaded file deleted cause for api not persist user.");
			}else if(apiResponse.getStatus() == 200){
				user = apiResponse.getData();
			}
			
			return user;
		}catch(DeskAppWebException  ee){
			logger.error("error while saving user.");
			throw new DeskAppWebException("error while saving user.", ee);
		}
	}

	@Override
	public User update(User user) {
		Gson gson = new Gson();
		String url = ip+port+api;
		
		Map<String, String> header = this.createHeaderInstance();
		
		try{
			
			this.validateUser(user);
			String data = new Gson().toJson(user);
			Response<User> apiResponse = gson.fromJson(this.sendPOST(url+Constants.USER_UPDATE_API, data, header),new TypeToken<Response<User>>(){}.getType());
			
			if(apiResponse.getStatus() != 200){
				fileUtilityService.deleteFile(user.getUserName(), "profile", user.getHashedUserImage());
				logger.error("uploaded file deleted cause for api not updated user.");
			}else if(apiResponse.getStatus() == 200){
				user = apiResponse.getData();
			}
			
			return user;
		}catch(DeskAppWebException  ee){
			logger.error("error while saving user.");
			throw new DeskAppWebException("error while updating user.", ee);
		}
	}
	
	private void validateUser(User user){
		
		if(ObjectUtils.isEmpty(user)){
			logger.info("user canot be null occur on web validation while saving.");
			throw new DeskAppWebException("user cannot empty while saving");
		}
		
		try{
			SessionConfig sessionConfig = new SessionConfig();
			if(sessionConfig.getCurrentUserId() == null && sessionConfig.getCurrentUserName() == null){
				logger.error("error while updating user.");
				throw new DeskAppWebException("error while updating user.");
			}
			user.setId(sessionConfig.getCurrentUserId());
			user.setUserName(sessionConfig.getCurrentUserName());
			
			if(!ObjectUtils.isEmpty(user.getUserProfileFileUpload()) && !ObjectUtils.isEmpty(user.getUserName())){
				user.setHashedUserImage(fileUtilityService.getFileName(user.getUserProfileFileUpload().getOriginalFilename()));
				fileUtilityService.saveFile(user.getUserProfileFileUpload(), user.getUserName(), "profile", user.getHashedUserImage());
			}
		}catch(DeskAppWebException e){
			logger.info("error occur on uploading profile while user saving.");
			throw new DeskAppWebException("user cannot empty while saving",e);
		}
	}
	
	@Override
	public List<User> getUsers() {
		Gson gson = new Gson();
		String url = ip+port+api;
		try{
			Map<String, String> header = this.createHeaderInstance();
			
			Response<List<User>> apiResponse = gson.fromJson(this.sendGET(url+Constants.USER_SAVE_API, header), new TypeToken<Response<List<User>>>(){}.getType());
			
			if(apiResponse.getStatus() == 200){
				List<User> users = gson.fromJson(gson.toJson(apiResponse.getData()), new TypeToken<List<User>>(){}.getType());
				
				return users;
			}
		}catch(Exception ee){
			logger.info("error while fetching all users.");
		}
		return null;
	}

	@Override
	public User getUserByUserName(String userName) {
		String url = ip+port+api;
		Gson gson = new Gson();
		if(ObjectUtils.isEmpty(userName)){
			return null;
		}
		User user = null;
		try{
			user = new User();
			
			Map<String, String> header = this.createHeaderInstance();
			url = String.format(url+Constants.USER_GET_BY_USERNAME_API, userName);
			
			Response<User> apiResponse = gson.fromJson(this.sendGET(url,  header), new TypeToken<Response<User>>(){}.getType()); 
			
			if(apiResponse.getStatus() == 200){
				user = gson.fromJson(gson.toJson(apiResponse.getData()), User.class);
				return user;
			}
		}catch(Exception ee){
			logger.error("error while fetching user by userName.");
		}
		return user;
	}

	@Override
	public boolean isLoggedIn(User user) {
		
		if(user != null){
			user = getUserByUserName(user.getUserName());
			try{
				if(user != null && user.getIsActive()){
					
					return true;
				}
			}catch(NullPointerException ee){
				logger.error("error while checking user loggedIn.");
				return false;
			}
		}
		logger.info("passed user may be loggedOff.");
		return false;
	}
}
