package com.controllers;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.HelperUtility;
import com.Response;
import com.google.gson.Gson;
import com.models.Book;
import com.models.User;

import com.services.UserService;

/**
 * @author RITESH SINGH
 * @since 2017-06-08
 * @version	1.8
 */
@Controller("userController")
@ComponentScan("com.services")
@RequestMapping("user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	/**
	 * <b>Create new User.</b>
	 * <h3>Request Method POST</h3>
	 * @param user : Object Type, param Type RequestBody
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 */
	@CrossOrigin 
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Response<User>> saveUser(@RequestBody User user,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response) throws UnknownHostException {
		
		return new ResponseEntity<Response<User>>(new Response<User>(
                HttpStatus.OK.value(), "User saved successfully.",userService.save(user)), HttpStatus.OK);
	}
	
	/**
	 * <b>Returns all users List</b>
	 * <h3>Request Method GET</h3>
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 */
	@CrossOrigin 
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Response<List<User>>> getUsers(
			@RequestParam(value = "q", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response) throws UnknownHostException {

		Long totalElements = userService.count();
		Page<User> pages = userService.getUsers(HelperUtility.getPageable(page, size, sort, totalElements));
		
		return new ResponseEntity<Response<List<User>>>(new Response<List<User>>(
                HttpStatus.OK.value(), "Fetched users successfully of given page.", pages.getContent()), HttpStatus.OK);
	}
	
	/**
	 * <b>Returns user of given userName</b>
	 * <h3>Request Method GET</h3>
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param userName : String Type, param Type PathVariable
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 */
	@CrossOrigin 
	@RequestMapping(value = "/{userName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Response<User>> getUserByUserName(
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			@PathVariable("userName") String userName, HttpServletResponse response) throws UnknownHostException {
		
		return new ResponseEntity<Response<User>>(new Response<User>(
                HttpStatus.OK.value(), "Fetched User by userName successfully.",userService.getUserByUserName(userName)), HttpStatus.OK);
	}
}
