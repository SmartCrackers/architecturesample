package com.services;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.Constants;
import com.DeskAppWebException;
import com.Response;
import com.dao.DataAccessObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.models.User;
import com.models.UserLog;

//@EnableAsync
@Service("asynchDeltaService")
public class AsynchDeltaServiceImpl extends DataAccessObject implements AsynchDeltaService {

	final static Logger logger = Logger.getLogger(AsynchDeltaServiceImpl.class);
	
	@Value("${ip}")
	private String ip;
	
	@Value("${port}")
	private String port;
	
	@Value("${api}")
	private String api;
	
	//@Async
	@Override
	public Integer createAccessLog(User user){
		Gson gson = new Gson();
		String url = ip+port+api;
		String data = new Gson().toJson(user);
		Integer userLog = null;
		Map<String, String> header = this.createHeaderInstance();
		
		try{
			Response<Integer> apiResponse = gson.fromJson(this.sendPOST(url+Constants.ASYNCH_LOGGER, data, header),new TypeToken<Response<Integer>>(){}.getType());
			
			if(apiResponse.getStatus() != 200){
				logger.error("User Login fail.");
			}else if(apiResponse.getStatus() == 200){
				userLog = apiResponse.getData();
			}
			
			return userLog;
		}catch(DeskAppWebException  ee){
			logger.error("error while user login.");
			throw new DeskAppWebException("error while user login.", ee);
		}
	}
}
