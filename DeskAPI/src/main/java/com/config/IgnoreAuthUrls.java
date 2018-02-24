package com.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class contains the all those URL list which will avoid from authentication 
 * @author RITESH SINGH
 *
 */
public class IgnoreAuthUrls {
	
	public static final String GET = "method:GET";
	public static final String POST = "method:POST";
	public static final String DELETE = "method:DELETE";

	public static final List<String> URLS = Stream.of(
										"/"+GET,
										"/user/*[a-zA-Z0-9]*"+GET,   
										"/web/*[A-Za-z0-9-*/%]*"+GET, /* All url allow which starts from web for only GET method  */
										"/user/"+POST,
										"/auth/login"+POST
										).collect(Collectors.toList());
}
