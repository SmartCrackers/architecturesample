package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.models.User;
import com.services.AsynchDeltaService;
import com.services.WebService;

@Controller
@ComponentScan("com.services,com.redis,com.threads")
@RequestMapping(value = "screen")
public class WebController {
	
	final static Logger LOGGER = Logger.getLogger(WebController.class);
	final static String PROFILE_PAGE = "web/profile";
	
	@Autowired
	private WebService webService;
	
	@Autowired
	private AsynchDeltaService asynchDeltaService;
	
	@RequestMapping(value = { "/profile/{userName}" }, method = RequestMethod.GET)
	public ModelAndView formLogin(@PathVariable("userName") String userName,
			HttpServletRequest request) throws IOException {
		
		System.out.println("HostName: "+request.getRemoteAddr());
		
		Map<String, Object> data = new HashMap<String, Object>(); 
		data.put("user", webService.getUserByUserName(userName));
		
		User user = new User();
		user.setAbout("sdgfsdgsdg");
		
		asynchDeltaService.createAccessLog(user);
		
		LOGGER.info("Accessed ScreenPage.");
		return new ModelAndView(PROFILE_PAGE, "data" , data);
	}
}
