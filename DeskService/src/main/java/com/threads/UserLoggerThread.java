package com.threads;

public class UserLoggerThread implements DeskAPIExecutorService<String> {

	private String key;
	private String value;
	
	public UserLoggerThread(String key,String value){
		this.key = key;
		this.value = value;
	}

	@Override
	public void run() {
	
		System.out.println("Key: "+this.key);
		System.out.println("Value: "+this.value);
	}
}
