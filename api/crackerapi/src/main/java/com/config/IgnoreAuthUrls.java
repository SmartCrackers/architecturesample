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

	public static final List<String> URLS = Stream.of(
										"/",
										"/employee/get-employee"
										).collect(Collectors.toList());
}
