package com;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public class Utility {

	public static Map<String, Object> getViewData(){
		Map<String, Object> data= new HashMap<String, Object>();
		NumberFormatter numberFormatter = new NumberFormatter();
		data.put("numberFormatter", numberFormatter);
		return data;
	}
}