package com.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
	
	private static final long serialVersionUID = 7746218756589213487L;
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String password;
	
	private String designation;
	
	private String companyName;
	
	private Integer handledProjects;
	
	private String about;
	
	private Long mob;
	
	private String state;
	
	private String email;
	
	private String recoveryEmail;
	
	private String address;
	
	private String twitter;
	
	private String github;
	
	private String stackoverflow;
	
	private String linkedIn;
	
	private Integer pincode;
	
	private String hashedUserImage;
	
	@Transient
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
