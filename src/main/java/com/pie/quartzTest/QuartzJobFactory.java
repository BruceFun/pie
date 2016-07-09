package com.pie.quartzTest;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class QuartzJobFactory implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("����ɹ�����");
        SchedulerConfig scheduleJob = (SchedulerConfig)context.getMergedJobDataMap().get("scheduleJob");
        System.out.println("�������� = [" + scheduleJob.getJobName() + "]");
	
	}

}
