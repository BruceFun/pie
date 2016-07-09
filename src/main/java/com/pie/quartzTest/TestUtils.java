package com.pie.quartzTest;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class TestUtils {
	private static SchedulerFactoryBean schedulerFactoryBean;
	@SuppressWarnings("static-access")
	public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean) {
		this.schedulerFactoryBean = schedulerFactoryBean;
	}

	public static void addJob(SchedulerConfig job) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
		//��ȡtrigger������spring�����ļ��ж���� bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		//�����ڣ�����һ��
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
					.withIdentity(job.getJobName(), job.getJobGroup())
					.build();
			jobDetail.getJobDataMap().put("scheduleJob", job);
			//���ʽ���ȹ�����
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			//���µ�cronExpression���ʽ����һ���µ�trigger
			trigger = TriggerBuilder.newTrigger()
					.withIdentity(job.getJobName(), job.getJobGroup())
					.withSchedule(scheduleBuilder)
					.build();
			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger�Ѵ��ڣ���ô������Ӧ�Ķ�ʱ����
			//���ʽ���ȹ�����
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			//���µ�cronExpression���ʽ���¹���trigger
			trigger = trigger.getTriggerBuilder()
					.withIdentity(triggerKey)
					.withSchedule(scheduleBuilder)
					.build();
			//���µ�trigger��������jobִ��
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}
}
