package com.pie.quartzTest;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ScheduleJob extends QuartzJobBean{
	private AnotherBean anotherBean;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		anotherBean.printAnotherMessage();
		
	}
	
	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}

}
