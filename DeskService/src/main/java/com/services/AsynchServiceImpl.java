package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.threads.DeskAPIExecutorService;
import com.threads.TaskExecutor;
import com.threads.UserLoggerThread;

@EnableAsync
@Service("asynchServiceImpl")
public class AsynchServiceImpl implements AsynchService {

	
	@Autowired
	@Qualifier("taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Async
	@Override
	public void createUserLog(String logData){
		
		DeskAPIExecutorService<String> serviceRoller = new UserLoggerThread("Hello-Key", "Hello-Value");
		taskExecutor.execute(serviceRoller);
	}
}
