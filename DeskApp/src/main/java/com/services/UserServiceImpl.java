package com.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.Constants;
import com.DeskAppWebException;
import com.Response;
import com.dao.DataAccessObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
	public void save(User user) {
		String url = ip+port+api;
		String data = new Gson().toJson(user);
		
		Map<String, String> header = new HashMap<String, String>();
		header.put("token", "myToken");
		
		try{
			user.setId("1");
			this.validateUser(user);
			String responseObject = this.sendPOST(url+Constants.USER_SAVE_API, data, header);
			logger.info("user saved.: "+ responseObject);
			if(responseObject.equals("500")){
				fileUtilityService.deleteFile(user.getId(), "profile", user.getHashedUserImage());
				logger.error("uploaded file deleted cause for api not persist user.");
			}
		}catch(DeskAppWebException  ee){
			logger.error("error while saving user.");
			throw new DeskAppWebException("error while saving user.", ee);
		}
	}

	private void validateUser(User user){
		
		if(ObjectUtils.isEmpty(user)){
			logger.info("user canot be null occur on web validation while saving.");
			throw new DeskAppWebException("user cannot empty while saving");
		}
		try{
			if(!ObjectUtils.isEmpty(user.getUserProfileFileUpload()) && !ObjectUtils.isEmpty(user.getId())){
				user.setHashedUserImage(fileUtilityService.getFileName(user.getUserProfileFileUpload().getName()));
				fileUtilityService.saveFile(user.getUserProfileFileUpload(), user.getId(), "profile", user.getHashedUserImage());
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
			Map<String, String> header = new HashMap<String, String>();
			
			header.put("token", "myToken");
			
			Response<List<User>> apiResponse = gson.fromJson(this.sendGET(url+Constants.USER_SAVE_API, header), Response.class);
			
			System.out.println("apiResponse = "+new Gson().toJson(apiResponse));
			
			if(apiResponse.getStatus() == 200){
				List<User> users = gson.fromJson(gson.toJson(apiResponse.getData()), List.class);
				logger.info("fetched users successfully.");
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
		try{
			User user = new User();
			
			Map<String, String> header = new HashMap<String, String>();
			header.put("token", "myToken");
			
			Response<User> apiResponse = gson.fromJson(this.sendGET(url+Constants.USER_SAVE_API+user.getUserName(),  header), Response.class); 
			System.out.println("apiResponse = "+new Gson().toJson(apiResponse));
			
			if(apiResponse.getStatus() == 200){
				user = gson.fromJson(gson.toJson(apiResponse.getData()), User.class);
				logger.info("fetched user by userName successfully.");
				return user;
			}
		}catch(Exception ee){
			logger.error("error while fetching user by userName.");
		}
		return null;
	}

	@Override
	public boolean isLoggedIn(User user) {
		
		if(user != null){
			user = getUserByUserName(user.getUserName());
			try{
				if(user != null && user.getIsActive()){
					logger.info("passed user loggedIn.");
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

	@Override
	public User setLoggedIn(User user) throws IOException {
		
		String url = ip+port+api;
		ObjectMapper mapper = new ObjectMapper();
		Gson gson = new Gson();
		if(user == null || user.getUserName() == null)
			return null;
			
		try{
			Map<String, String> header = new HashMap<String, String>();

			Response<User> apiResponse = gson.fromJson(this.sendPOST(url,gson.toJson(user),  header), Response.class); 
			System.out.println("apiResponse = "+new Gson().toJson(apiResponse));
			
			if(apiResponse.getStatus() == 200){
				user = mapper.readValue(gson.toJson(apiResponse.getData()), User.class);
				logger.info("fetched user by userName successfully.");

				return user;
			}
		}catch(Exception ee){
			logger.error("error while login user.");
			ee.printStackTrace();
		}
		return user;
		
	}
	
	@Override
	public boolean setLoggedOut(User user) throws IOException {
		try{
			if(user != null){
				user = this.getUserByUserName(user.getUserName());
				if(user != null){
					user.setIsActive(false);
					save(user);
					logger.info("user logOff successfully.");
					return true;
				}
			}
		}catch(Exception ee){
			logger.error("error while user logoff.");
		}
		logger.info("requested user for logOff not found.");
		return false;
	}
}
