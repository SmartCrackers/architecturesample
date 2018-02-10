package com.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Constants;
import com.ThymeleafUtility;
import com.google.gson.Gson;
import com.models.User;
import com.services.UserService;

@Controller
@ComponentScan("com.services")
@RequestMapping(value = "user")
public class UserController {
	
	final static Logger LOGGER = Logger.getLogger(UserController.class);
	
	final static String VIEW ="app/user";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView formLogin() throws IOException {
		
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = { "/profile/{userName}" }, method = RequestMethod.GET)
	public ModelAndView getProfileForm() throws IOException {
		Map<String, Object> data = ThymeleafUtility.getViewData(VIEW,"profileUpdateFragment","userProfileJsFragment");
		data.put("content", "Woowwwww"); 
		LOGGER.info("Accessed user profile update form.");
		return new ModelAndView(Constants.BASE_LAYOUT_PAGE, "data", data);
	}
	
	@RequestMapping(value = { "/profile" }, method = RequestMethod.POST)
	public ModelAndView saveProfile(@ModelAttribute("userProfile") User user, BindingResult result,
			HttpServletRequest request) throws IOException {
		
		userService.save(user);
		
		Map<String, Object> data = ThymeleafUtility.getViewData(VIEW,"profileUpdateFragment","userProfileJsFragment");
		data.put("content", "Woowwwww"); 
		LOGGER.info("User profile updated.");
		return new ModelAndView(Constants.BASE_LAYOUT_PAGE, "data", data);
	}
}
