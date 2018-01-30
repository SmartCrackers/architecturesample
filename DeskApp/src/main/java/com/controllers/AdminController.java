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
}
