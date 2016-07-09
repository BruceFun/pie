package com.pie.quartzJob;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pie.utils.QuartzJobFactory;

public class Job_test3 extends QuartzJobFactory{
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("任务调度333333333333333333……成功执行");
	}
}
