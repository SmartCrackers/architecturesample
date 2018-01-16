package com.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.Constants;
import com.HelperUtility;
import com.exception.BookException;

/**
 * @author RITESH SINGH
 *
 */
@Document(collection = "users_log")
public class UserLog implements Serializable {
	
	private static final long serialVersionUID = 7746218756589213487L;

	@Id
	private String id;
	private String userName;
	private String email;
	private String password;
	private String token;
	private Boolean isExpired;
	
	private long createdAt;
	
	/**
	 * Token active period in milliseconds
	 */
	private long tokenLife;
	
	private long expiredAt;
	
	/*
	 * if user logout then expiredType will be normal=1
	 * if token time stamp exceeded then expiredType will be timeStampTriggered=2
	 * if token set expired forcefully by Admin then  expiredType will be forced=3
	 */
	private int expiredType;
	
	public UserLog(User user){
		this.createdAt = new Date().getTime();
		this.tokenLife = Constants.TOKEN_LIFE;
		this.expiredAt = this.createdAt + this.tokenLife;
		this.isExpired = false;
		
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.token = HelperUtility.getToken(this.userName);
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

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public long getTokenLife() {
		return tokenLife;
	}

	public long getExpiredAt() {
		return expiredAt;
	}

	public int getExpiredType() {
		return expiredType;
	}

	public void setExpiredType(int expiredType) {
		this.expiredType = expiredType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int tokenStatus(){
		if(!this.isExpired)
			if((new Date().getTime()) < this.expiredAt)
				return Constants.ACTIVE_TOKEN;
		
		return Constants.EXPIRED_TOKEN;
	}
}
