package com;

import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;

public class ThymeleafUtility {

	private static String JS_FRAGMENTS_LOCATION = "fragments/js-fragments";
	
	public static Map<String, Object> getViewData(@NonNull String path,@NonNull String fragment){
		
		return getMap(path,fragment);
	}
	public static Map<String, Object> getViewData(@NonNull String path,@NonNull String fragment,@NonNull String jsFragment){
		Map<String, Object> data=getMap(path,fragment);
		data.put("jsFragment", jsFragment);
		
		return data;
	}
	
	private static Map<String, Object> getMap(@NonNull String path,@NonNull String fragment){
		Map<String, Object> data= new HashMap<String, Object>();
		NumberFormatter numberFormatter = new NumberFormatter();
		data.put("numberFormatter", numberFormatter);
		data.put("path", path);
		data.put("fragment", fragment);
		data.put("jsFragment", null);
		data.put("jsFragmentLocation", JS_FRAGMENTS_LOCATION);
		return data;
	}
}