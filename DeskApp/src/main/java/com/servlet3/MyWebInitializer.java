package com.servlet3;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.config.BaseFilter;
import com.config.RedisConfig;
import com.config.SpringWebConfig;

/**
 * @author RITESH SINGH
 *
 */
public class MyWebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer  {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class, RedisConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ApplicationConfig.class};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new BaseFilter()};
	}
}