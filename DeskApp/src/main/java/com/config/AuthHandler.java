package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthHandler {

	@Bean(initMethod = "init", destroyMethod = "cleanup" )
	public SecureRequestImpl tokenHandlerImpl(){
		return new SecureRequestImpl();
	}
}

interface SecureRequest{
	
	void sayHello();
}

class SecureRequestImpl implements SecureRequest {

	public void init(){
		
		System.out.println("Init ");
	}
	
	@Override
	public void sayHello() {
		System.out.println("Secure Request.");
	}
	
	public void cleanup() {
		System.out.println("cleanup ");
   }
}