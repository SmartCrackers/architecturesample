package com.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Constants;
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
	
	@Override
	public void save(User user) throws IOException {
		String url = ip+port+api;
		String data = new Gson().toJson(user);
		
		Map<String, String> header = new HashMap<String, String>();
		
		header.put("token", "myToken");
		
		try{
			sendPOST(url+Constants.USER_SAVE_API, data, header);
			logger.info("user saved.");
		}catch(Exception ee){
			logger.error("error while saving user.");
		}
	}

	@Override
	public List<User> getUsers() {
		Gson gson = new Gson();
		String url = ip+port+api;
		try{
			Map<String, String> header = new HashMap<String, String>();
			
			header.put("token", "myToken");
			
			Response<List<User>> apiResponse = gson.fromJson(sendGET(url+Constants.USER_SAVE_API, header), Response.class);
			
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
	public User getUserByUserName(User user) {
		String url = ip+port+api;
		Gson gson = new Gson();
		if(user == null || user.getUserName() == null)
			return null;
			
		try{
			Map<String, String> header = new HashMap<String, String>();
			header.put("token", "myToken");
			
			Response<User> apiResponse = gson.fromJson(sendGET(url+Constants.USER_SAVE_API+user.getUserName(),  header), Response.class); 
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
			user = getUserByUserName(user);
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

			Response<User> apiResponse = gson.fromJson(sendPOST(url,gson.toJson(user),  header), Response.class); 
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
				user = getUserByUserName(user);
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
