package com.controllers;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.Response;
import com.models.User;
import com.models.UserLog;
import com.services.AuthService;

/**
 * @author RITESH SINGH
 * @since 2017-06-08
 * @version	1.8
 *
 */
@Controller("authController")
@ComponentScan("com.services")
@RequestMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

	final static Logger LOGGER = Logger.getLogger(AuthController.class);
	
	@Autowired
	@Qualifier("authService")
	private AuthService authService;
	
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<UserLog>> saveBookAccount(
			@RequestBody User userCredential,
			HttpServletResponse response)throws UnknownHostException {

		LOGGER.info("user saved api accessed.");
		return new ResponseEntity<Response<UserLog>>(new Response<UserLog>(
                HttpStatus.OK.value(), "Login successfully.",authService.login(userCredential)), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/logout", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBookAccountsPageable(
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		return new ResponseEntity<Response>(
				new Response(200, "Logout successfully.",authService.loggedOut(accessToken)),
				HttpStatus.OK);
	}
}
