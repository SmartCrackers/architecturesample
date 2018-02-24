package com.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.Constants;
import com.HelperUtility;
import com.exception.BookException;
import com.modelUtility.EditableInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author RITESH SINGH
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users_log")
public class UserLog implements Serializable {
	
	private static final long serialVersionUID = 7746218756589213487L;

	@Id
	private String id;
	private String userId;
	private String userName;
	private String email;
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
	
	public int tokenStatus(){
		if(!this.isExpired)
			if((new Date().getTime()) < this.expiredAt)
				return Constants.ACTIVE_TOKEN;
		
		return Constants.EXPIRED_TOKEN;
	}
}
