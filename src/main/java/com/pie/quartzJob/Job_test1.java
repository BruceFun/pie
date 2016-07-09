package com.pie.quartzJob;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pie.utils.QuartzJobFactory;

public class Job_test1 extends QuartzJobFactory{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String id = (String)context.getMergedJobDataMap().get("businessId");
		System.out.println("任务调度1——用户类任务——……成功执行");
	}
}
