package com.models;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class UserLog {

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
	
}
