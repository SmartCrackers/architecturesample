package com.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;
import com.config.quartz.QuartzConfig;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FormAggregation implements Job {

	@Value("${cron.expression.formAggregation}")
	private String cronExpression;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println("Form Aggrigation stop..!");
	}
	
	@Bean(name = "formAggregationJobBean")
	public JobDetailFactoryBean formAggregationJobBean() {
		return QuartzConfig.createJobDetail(this.getClass());
	}
	
	@Bean(name = "formAggregationJobBeanTrigger")
	public CronTriggerFactoryBean formAggregationJobBeanTrigger(
			@Qualifier("formAggregationJobBean") JobDetailFactoryBean fetchScheduledJobBean) {
		return QuartzConfig.createCronTrigger(fetchScheduledJobBean.getObject(), "0 0/2 * 1/1 * ? *");
	}
}
