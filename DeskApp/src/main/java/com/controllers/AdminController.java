package com.controllers;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan("com.services,com.redis")
@RequestMapping(value = "admin")
public class AdminController {

	final static Logger logger = Logger.getLogger(AdminController.class);
	
}
