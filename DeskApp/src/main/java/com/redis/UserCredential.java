package com.redis;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.models.User;

public class UserCredential implements Serializable {

	private static final long serialVersionUID = 7746218756589213487L;
	
	@Id
	private String id;
	private String userName;
	private String userDisplayName;
    private String email;
    private String token;
    private Boolean tokenStatus;
    private Date createdAt;
    
    public UserCredential(){}
    
    public UserCredential(User user){
    	if(user!=null){
    		this.id = user.getId();
    		this.userName = user.getUserName();
    		this.userDisplayName = user.getFirstName()+" "+user.getLastName();
    		this.email = user.getEmail();
    		//this.token = user.getToken();
    		this.token = "Token-updated";
    		this.tokenStatus = !user.getIsTokenExpired();
    		Date d = new Date();
    		d.setTime(user.getTokenCreatedAt());
    		this.createdAt = d;
    	}
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getTokenStatus() {
		return tokenStatus;
	}
	public void setTokenStatus(Boolean tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
}
