package com.pie.quartzJob;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pie.utils.QuartzJobFactory;

public class Job_test2 extends QuartzJobFactory{
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("�������22222222222222�����ɹ�ִ��");
	}
}
