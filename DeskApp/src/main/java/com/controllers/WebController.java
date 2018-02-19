package com.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan("com.services,com.redis")
@RequestMapping(value = "screen")
public class WebController {
	
	final static Logger LOGGER = Logger.getLogger(WebController.class);
	final static String PROFILE_PAGE = "web/profile";
	
	@RequestMapping(value = { "/profile/{userName}" }, method = RequestMethod.GET)
	public ModelAndView formLogin(@PathVariable("userName") String userName,
			HttpServletRequest request) throws IOException {
		
		LOGGER.info("Accessed ScreenPage.");
		return new ModelAndView(PROFILE_PAGE);
	}
}
