package com.controllers;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Response;
import com.models.User;
import com.services.AsynchService;

@Controller("asynchController")
@ComponentScan("com.services,com.threads")
@RequestMapping("asynch-delta")
public class AsynchController {

	final static Logger LOGGER = Logger.getLogger(AsynchController.class);
	
	@Autowired
	private AsynchService asynchService;
	
	@CrossOrigin
	@RequestMapping(value = "create/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Response<Integer>> saveUser(
			@RequestBody  User user,
			HttpServletResponse response) throws UnknownHostException {
		
		asynchService.createUserLog("Data-Log");
		asynchService.createUserLog("Data-Log");
		asynchService.createUserLog("Data-Log");
		asynchService.createUserLog("Data-Log");
		asynchService.createUserLog("Data-Log");
		asynchService.createUserLog("Data-Log");
		LOGGER.info("fetch user profile by username, api accessed.");
		return new ResponseEntity<Response<Integer>>(new Response<Integer>(
                HttpStatus.OK.value(), "Fetched User by userName successfully.",201), HttpStatus.OK);
	}
}
