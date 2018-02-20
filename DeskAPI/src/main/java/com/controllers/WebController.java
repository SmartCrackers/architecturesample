package com.controllers;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Response;
import com.models.User;
import com.services.UserService;

@Controller("webController")
@ComponentScan("com.services")
@RequestMapping("web")
public class WebController {

final static Logger LOGGER = Logger.getLogger(WebController.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@CrossOrigin 
	@RequestMapping(value = "get-user/{userName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Response<User>> getUserByUserName(
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			@PathVariable("userName") String userName, HttpServletResponse response) throws UnknownHostException {
		
		LOGGER.info("fetch user profile by username, api accessed.");
		return new ResponseEntity<Response<User>>(new Response<User>(
                HttpStatus.OK.value(), "Fetched User by userName successfully.",userService.getUserByUserName(userName)), HttpStatus.OK);
	}
}
