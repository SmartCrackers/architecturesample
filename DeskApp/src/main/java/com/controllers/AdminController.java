package com.controllers;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Constants;
import com.Response;
import com.ThymeleafUtility;
import com.google.gson.Gson;
import com.models.User;

@Controller
@ComponentScan("com.services,com.redis")
@RequestMapping(value = "auth")
public class AdminController {

	final static Logger LOGGER = Logger.getLogger(AdminController.class);
	
	final static String LOGIN_PAGE = "admin/login";
	final static String VIEW ="app/logged-in-welcome";

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView formLogin() throws IOException {
		
		LOGGER.info("Accessed login page.");
		return new ModelAndView(LOGIN_PAGE);
	}

	@CrossOrigin
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Response<User>> createUser(@RequestBody User user, Model model){
		Gson g = new Gson();
		System.out.println(g.toJson(user));
		
		return new ResponseEntity<Response<User>>(new Response<User>(
				HttpStatus.OK.value(), "User data saved successfully.", user), HttpStatus.OK);
	}

	@RequestMapping(value = { "/logged-in-index" }, method = RequestMethod.GET)
	public ModelAndView loggedInIndex() throws IOException {
		
		Map<String, Object> data = ThymeleafUtility.getViewData(VIEW,"landingFragment");
		data.put("content", "Woowwwww");
		LOGGER.info("Accessed login page.");
		return new ModelAndView(Constants.BASE_LAYOUT_PAGE, "data", data);
	}
}