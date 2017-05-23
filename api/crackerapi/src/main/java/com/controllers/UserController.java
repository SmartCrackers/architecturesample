package com.controllers;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Response;
import com.google.gson.Gson;
import com.models.User;

import com.services.UserService;

/**
 * @author RITESH SINGH
 *
 */
@Controller("userController")
@ComponentScan("com.services")
@RequestMapping("user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@CrossOrigin 
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String saveUser(ModelMap model, @RequestBody User user,
			@RequestHeader(value = "token", defaultValue = "foo") String userAgent, HttpServletResponse response)
			throws UnknownHostException {
		Response responseData = new Response();
		responseData.setData(user);
		responseData.setStatus(200);
		responseData.setStatusDescription("success");
		try{
			if(user != null)
				userService.saveUser(user);
		}catch(Exception ee){
			System.out.println("Error while saving user!");
			responseData.setStatus(500);
			responseData.setStatusDescription("Internal Server Error");
		}
		return new Gson().toJson(responseData);
	}
	
	@CrossOrigin 
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getUsers(ModelMap model,
			@RequestHeader(value = "token", defaultValue = "foo") String userAgent, HttpServletResponse response)
			throws UnknownHostException {

		List<User> users = null;	
		Response responseData = new Response();
		try{
			users = userService.getUsers();
			responseData.setData(users);
			responseData.setStatus(200);
			responseData.setStatusDescription("success");
		}catch(Exception ee){
			System.out.println("Error while fetching users!");
			responseData.setData(null);
			responseData.setStatus(500);
			responseData.setStatusDescription("Internal Server Error");
		}
		
		return new Gson().toJson(responseData);
	}
	
	@CrossOrigin 
	@RequestMapping(value = "/{userName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getUserByUserName(ModelMap model,
			@RequestHeader(value = "token", defaultValue = "foo") String userAgent,@PathVariable("userName") String userName, HttpServletResponse response)
			throws UnknownHostException {
		Response responseData = new Response();
		User user = null;	
		
		try{
			user = userService.getUserByUserName(userName);
			responseData.setData(user);
			responseData.setStatus(200);
		}catch(Exception ee){
			System.out.println("Error while fetching user by username!");
			responseData.setData(null);
			responseData.setStatus(500);
			responseData.setStatusDescription("Internal Server Error");
		}
		
		return new Gson().toJson(responseData);
	}
}
