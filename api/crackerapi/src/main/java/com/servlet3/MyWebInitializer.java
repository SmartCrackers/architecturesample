package com.servlet3;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.config.ApplicationFilter;
import com.config.BaseFilter;
import com.config.SpringMongoConfig1;
import com.config.SpringWebConfig;

/**
 * @author RITESH SINGH
 *
 */
public class MyWebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ApplicationConfig.class, SpringMongoConfig1.class};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new BaseFilter(),new ApplicationFilter()};
	}

}