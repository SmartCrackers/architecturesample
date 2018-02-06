package com.models;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modelUtility.EditableInfo;

import lombok.Data;

/**
 * @author RITESH SINGH
 *
 */
@Data
public class User implements Serializable {
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String password;
	
	private String email;
	
	private String userImage;
	
	private String hashedUserImage;
	
	private MultipartFile userProfileFileUpload;
	
	private Boolean isActive;
	
	private EditableInfo editableInfo;
	
	private String token;
	
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
