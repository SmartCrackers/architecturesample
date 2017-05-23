package com.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * This Filter is not developed yet for use
 * @author RITESH SINGH
 *
 */
@WebFilter("/cool/*")
public class ApplicationFilter implements Filter{
	
	private ServletContext context;

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Base-Filter Initialization");
		this.context = filterConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()){
			String name = params.nextElement();
			String value = request.getParameter(name);
			this.context.log(request.getRemoteAddr() + "::Request Params::{"+name+"="+value+"}");
		}
		
		System.out.println("Base-Filter Start");
        System.out.println(request.getProtocol()+" : "+request.getLocalAddr());
        System.out.println("Base-Filter Stop"); 
        //  pass the request along the filter chain
        chain.doFilter(request,response);
		
	}

	public void destroy() {
		System.out.println("Base-Filter Destroy.");
	}
}