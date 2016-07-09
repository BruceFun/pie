package com.pie.utils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pie.domain.SchedulerJob;

public class QuartzJobFactory implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("����ɹ�����");
		SchedulerJob scheduleJob = (SchedulerJob)context.getMergedJobDataMap().get("schedulerJob");
        System.out.println("�������� = [" + scheduleJob.getJobDesc() + "]");
	}
}