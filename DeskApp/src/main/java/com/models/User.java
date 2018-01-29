package com.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modelUtility.EditableInfo;

/**
 * @author RITESH SINGH
 *
 */
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
	
	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getHashedUserImage() {
		return hashedUserImage;
	}

	public void setHashedUserImage(String hashedUserImage) {
		this.hashedUserImage = hashedUserImage;
	}

	public String getUserCoverImage() {
		return userCoverImage;
	}

	public void setUserCoverImage(String userCoverImage) {
		this.userCoverImage = userCoverImage;
	}

	public String getHashedUserCoverImage() {
		return hashedUserCoverImage;
	}

	public void setHashedUserCoverImage(String hashedUserCoverImage) {
		this.hashedUserCoverImage = hashedUserCoverImage;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public EditableInfo getEditableInfo() {
		return editableInfo;
	}

	public void setEditableInfo(EditableInfo editableInfo) {
		this.editableInfo = editableInfo;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public com.redis.UserCredential getRedisUserCredential(){
		com.redis.UserCredential userCredential = new com.redis.UserCredential(this);
		return userCredential;
	}
}
