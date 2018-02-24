package com.services;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.Constants;
import com.Response;
import com.dao.DataAccessObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.models.User;

@Service("webService")
public class WebServiceImpl extends DataAccessObject implements WebService {

	final static Logger logger = Logger.getLogger(WebServiceImpl.class);
	
	@Value("${ip}")
	private String ip;
	
	@Value("${port}")
	private String port;
	
	@Value("${api}")
	private String api;
	
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
			
			url = String.format(url+Constants.WEB_USER_GET_BY_USERNAME_API, userName);
			
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
}
