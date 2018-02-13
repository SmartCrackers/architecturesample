package com.config;

import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.Constants;

public class SessionConfig {

	private HttpSession session;
	
	public SessionConfig(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		this.session= attr.getRequest().getSession(true);
	}
	
	public SessionConfig(String userId, String userName, String token){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    this.session= attr.getRequest().getSession(true);
	    this.session.setAttribute(Constants.USER_ID, userId);
	    this.session.setAttribute(Constants.USER_NAME, userName);
	    this.session.setAttribute(Constants.AUTH_HEADER_TOKEN, token);
	}
	
	public String getCurrentUserId(){
		return (String) this.session.getAttribute(Constants.USER_ID);
	}
	
	public String getCurrentUserName(){
		return (String) this.session.getAttribute(Constants.USER_NAME);
	}
	
	public String getCurrentToken(){
		return (String) this.session.getAttribute(Constants.AUTH_HEADER_TOKEN);
	}
}