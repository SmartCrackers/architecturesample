package com.controllers;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Constants;
import com.ThymeleafUtility;

@Controller
@ComponentScan("com.services")
@RequestMapping(value = "user")
public class UserController {
	
	final static Logger LOGGER = Logger.getLogger(UserController.class);
	
	final static String VIEW ="app/user";
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView formLogin() throws IOException {
		
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	public ModelAndView formLoginTest() throws IOException {
		Map<String, Object> data = ThymeleafUtility.getViewData(VIEW,"profileUpdateFragment","userProfileJsFragment");
		data.put("content", "Woowwwww"); 
		LOGGER.info("Accessed login page.");
		return new ModelAndView(Constants.BASE_LAYOUT_PAGE, "data", data);
	}
}
