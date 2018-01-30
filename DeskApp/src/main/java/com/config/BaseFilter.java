package com.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.Response;
import com.google.gson.Gson;

/**
 * This the Base Filter for every request
 * 
 * @author RITESH SINGH
 *
 */
@WebFilter("/*")
public class BaseFilter implements Filter {

	private ServletContext context;

	private SecureRequest secureRequest;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(AuthHandler.class);   
		this.secureRequest = ctx.getBean(SecureRequestImpl.class);
		this.context = filterConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = params.nextElement();
			String value = request.getParameter(name);
			this.context.log(request.getRemoteAddr() + "::Request Params::{" + name + "=" + value + "}");
		}

		try {
			if (!isIgnoreUrlForAuth(request)) {
				if(false){
					request.setAttribute("errorMessage", "");
					request.getRequestDispatcher("/user/test")
		                               .forward(request, response);
				}
			}
		} catch (Exception e) {
			/*request.setAttribute("errorMessage", e);
			request.getRequestDispatcher("/user/test")
                               .forward(request, response);*/
		}
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		System.out.println("Base-Filter Destroy.");
	}

	/**
	 * @param request
	 * @return true if requestedUrl matched from ignoreUrl List
	 */
	private boolean isIgnoreUrlForAuth(ServletRequest request) {

		/**
		 * This class validate the requestedUrl from ignoreUrl List to avoid
		 * authentication
		 * 
		 * @author RITESH SINGH
		 */
		class RequestUrl {

			/**
			 * @return requestedAppUrl without http://yourhost:port/appname
			 */
			private String getRequestedUrl() {
				HttpServletRequest req = (HttpServletRequest) request;
				String appName = request.getServletContext().getContextPath();
				String requestedCompleteUrl = req.getRequestURL().toString();
				int i = requestedCompleteUrl.indexOf(appName) + appName.length();
				String requestedAppUrl = requestedCompleteUrl.substring(i);
				/*System.out.println(requestedAppUrl+"method:"+req.getMethod());*/
				return requestedAppUrl+"method:"+req.getMethod();
			}

			/**
			 * @return true if requestedAppUrl matched in ignoreUrl list
			 */
			protected boolean getIgnoreStatus() {
				if (IgnoreAuthUrls.URLS.contains(getRequestedUrl()))
					return true;
				else{
					if(getRequestedUrl().contains("resources"))
						return true;
					return false;
				}
			}
		}
		return new RequestUrl().getIgnoreStatus();
	}
}