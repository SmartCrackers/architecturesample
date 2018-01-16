package com.servlet3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author RITESH SINGH
 *
 */
@Configuration
@ComponentScan(basePackageClasses = Application.class)
class ApplicationConfig {

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(50 * 1024 * 1024);
		return multipartResolver;
	}
}