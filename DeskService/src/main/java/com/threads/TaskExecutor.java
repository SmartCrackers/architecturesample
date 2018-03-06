package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service("taskExecutor")
public class TaskExecutor {

  private static final ExecutorService executorService = new ThreadPoolExecutor(50, 200, 5,
      TimeUnit.SECONDS, new LinkedBlockingQueue<>(500), new ThreadPoolExecutor.CallerRunsPolicy());

  @Async
  public static void execute(Runnable task) {
    executorService.execute(task);
  }

}
