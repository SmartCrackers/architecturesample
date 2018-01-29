package com.controllers;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Utility;

@Controller
@ComponentScan("com.services")
@RequestMapping(value = "user")
public class UserController {
	
	final static Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView formLogin() throws IOException {
		Map<String, Object> data = Utility.getViewData();
		
		data.put("content", "Woowwwww");
		logger.info("Accessed book-list.");
		return new ModelAndView("home", "data", data);
	}
	
	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public ModelAndView formLoginTest() throws IOException {
		Map<String, Object> data = Utility.getViewData();
		
		data.put("content", "Woowwwww");
		logger.info("Accessed book-list.");
		return new ModelAndView("error1", "data", data);
	}
}
