package com.redis;

public class RedisError extends RuntimeException {

	private static final long serialVersionUID = 2771174581631905388L;
	
	public RedisError() {}
	
	public RedisError(String error) {
		super(error);
	}
	
	public RedisError(Throwable throwable){
		super(throwable);
	}
	
	public RedisError(String error,Throwable throwable) {
		super(error,throwable);
	}
	
	public RedisError(String error,Exception e) {
		super(error,e);
	}
}
