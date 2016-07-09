package com.pie.quartzJob;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pie.utils.QuartzJobFactory;

public class Job_test2 extends QuartzJobFactory{
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("任务调度22222222222222……成功执行");
	}
}
