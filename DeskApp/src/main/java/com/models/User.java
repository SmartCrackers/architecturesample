package com.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modelUtility.EditableInfo;

import lombok.Data;

/**
 * @author RITESH SINGH
 *
 */
@Data
public class User implements Serializable {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String displayName;
	private String userName;
	private String password;
	private String email;
	private String userImage;
	private String hashedUserImage;
	private String userCoverImage;
	private String hashedUserCoverImage;
	private Boolean isActive;
	private EditableInfo editableInfo;
	
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsTokenExpired() {
		return isTokenExpired;
	}

	public void setIsTokenExpired(Boolean isTokenExpired) {
		this.isTokenExpired = isTokenExpired;
	}

	public long getTokenCreatedAt() {
		return tokenCreatedAt;
	}

	public void setTokenCreatedAt(long tokenCreatedAt) {
		this.tokenCreatedAt = tokenCreatedAt;
	}

	public long getTokenLife() {
		return tokenLife;
	}

	public void setTokenLife(long tokenLife) {
		this.tokenLife = tokenLife;
	}

	public long getTokenExpiredAt() {
		return tokenExpiredAt;
	}

	public void setTokenExpiredAt(long tokenExpiredAt) {
		this.tokenExpiredAt = tokenExpiredAt;
	}

	public int getTokenExpiredType() {
		return tokenExpiredType;
	}

	public void setTokenExpiredType(int tokenExpiredType) {
		this.tokenExpiredType = tokenExpiredType;
	}

	@JsonProperty("isExpired")
	private Boolean isTokenExpired;
	
	@JsonProperty("createdAt")
	private long tokenCreatedAt;
	
	/**
	 * Token active period in milliseconds
	 */
	private long tokenLife;
	
	@JsonProperty("expiredAt")
	private long tokenExpiredAt;
	
	/*
	 * if user logout then expiredType will be normal=1
	 * if token time stamp exceeded then expiredType will be timeStampTriggered=2
	 * if token set expired forcefully by Admin then  expiredType will be forced=3
	 */
	@JsonProperty("expiredType")
	private int tokenExpiredType;
}
