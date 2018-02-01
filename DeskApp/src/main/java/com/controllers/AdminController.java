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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Response;
import com.Utility;
import com.google.gson.Gson;
import com.models.User;

@Controller
@ComponentScan("com.services,com.redis")
@RequestMapping(value = "auth")
public class AdminController {

	final static Logger logger = Logger.getLogger(AdminController.class);

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView formLogin() throws IOException {
		Map<String, Object> data = Utility.getViewData();
		
		data.put("content", "Woowwwww");
		logger.info("Accessed login page.");
		return new ModelAndView("admin/login", "data", data);
	}

	@RequestMapping(value = { "/logged-in-index" }, method = RequestMethod.GET)
	public ModelAndView loggedInIndex() throws IOException {
		Map<String, Object> data = Utility.getViewData();
		
		data.put("content", "Woowwwww");
		logger.info("Accessed login page.");
		return new ModelAndView("app/logged-in-welcome", "data", data);
	}
	
	@CrossOrigin
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Response<FormUser>> createUser(@RequestBody FormUser user, Model model){
		Gson g = new Gson();
		System.out.println(g.toJson(user));
		return new ResponseEntity<Response<FormUser>>(new Response<FormUser>(
                HttpStatus.OK.value(), "User data saved successfully.", user), HttpStatus.OK);
	}
}

class FormUser{
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}