package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author RITESH SINGH
 *
 */
@Controller
@RequestMapping(value = "users")
public class UserController {

	@RequestMapping(value = { "/template" }, method = RequestMethod.GET)
	public ModelAndView form() throws IOException {

		Map<String, Object> data = new HashMap<String, Object>();

		return new ModelAndView("user/newuser", "data", data);
	}

	@RequestMapping(value = { "/app" }, method = RequestMethod.GET)
	public ModelAndView userApp() throws IOException {

		Map<String, Object> data = new HashMap<String, Object>();

		return new ModelAndView("app/user", "data", data);
	}

	@RequestMapping(value = { "/twitter" }, method = RequestMethod.GET)
	public ModelAndView formTwitter() throws IOException {

		Map<String, Object> data = new HashMap<String, Object>();

		return new ModelAndView("twitter/login", "data", data);
	}
}
