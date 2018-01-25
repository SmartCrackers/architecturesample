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
@RequestMapping(value = "admin")
public class AdminController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() throws IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		return new ModelAndView("app/index", "data", data);
	}
}
