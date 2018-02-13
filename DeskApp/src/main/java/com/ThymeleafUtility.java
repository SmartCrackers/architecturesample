package com;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.config.SessionConfig;
import com.controllers.AdminController;

import lombok.NonNull;

public class ThymeleafUtility {
	
	final static Logger LOGGER = Logger.getLogger(ThymeleafUtility.class);

	private static String JS_FRAGMENTS_LOCATION = "fragments/js-fragments";
	
	public static Map<String, Object> getViewData(@NonNull String path,@NonNull String fragment){
		
		return getMap(path,fragment);
	}
	public static Map<String, Object> getViewData(@NonNull String path,@NonNull String fragment,@NonNull String jsFragment){
		Map<String, Object> data=getMap(path,fragment);
		data.put("jsFragment", jsFragment);
		
		return data;
	}
	
	private static Map<String, Object> getMap(@NonNull String path,@NonNull String fragment){
		Map<String, Object> data= new HashMap<String, Object>();
		NumberFormatter numberFormatter = new NumberFormatter();
		
		SessionConfig sessionConfig = new SessionConfig();
		if(sessionConfig.getCurrentUserId() == null && sessionConfig.getCurrentUserName() == null){
			LOGGER.error("session expired.");
			throw new DeskAppWebException("session expired.");
		}else{
			data.put("USERNAME", sessionConfig.getCurrentUserName());
		}
		
		data.put("numberFormatter", numberFormatter);
		data.put("path", path);
		data.put("fragment", fragment);
		data.put("jsFragment", null);
		data.put("jsFragmentLocation", JS_FRAGMENTS_LOCATION);
		return data;
	}
}