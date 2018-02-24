package com.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modelUtility.EditableInfo;

import lombok.Data;

/**
 * @author RITESH SINGH
 *
 */
@Data
@Document(collection = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 7746218756589213487L;
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String designation;
	
	private String password;
	
	private String about;
	
	private String email;
	
	private String twitter;
	
	private String github;
	
	private String stackoverflow;
	
	private String linkedIn;
	
	private String companyName;
	
	private Integer handledProjects;
	
	private String hashedUserImage;
	
	private Long mob;
	
	private String state;
	
	private String recoveryEmail;
	
	private String address;
	
	private Integer pincode;
	
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
