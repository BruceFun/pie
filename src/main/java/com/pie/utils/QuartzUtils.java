package com.pie.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pie.domain.SchedulerJob;

/**
 * ��������+��������һ������
 * @author bruce_000
 * 
 * С��ʾ
	���±��ʽ���жϱ��ʽ�Ƿ���ȷ����һ�´���
	CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("xxxxx");
 *
 */
@SuppressWarnings("all")
public class QuartzUtils {
	// ���������
	private static Scheduler scheduler;

	static{
		//schedulerFactoryBean ��spring����ע��
		ApplicationContext ctx = new ClassPathXmlApplicationContext("quartz-context.xml");  
		System.out.println(ctx);
		scheduler = (Scheduler)ctx.getBean("schedulerFactoryBean");
		System.out.println("static�е�" + scheduler);

	}

	/**
	 * �õ�һ�� Scheduler ����
	 * @return
	 */
	public static Scheduler getScheduler(){
		if(scheduler == null){
			ApplicationContext ctx = new ClassPathXmlApplicationContext("quartz-context.xml");  
			scheduler = (Scheduler)ctx.getBean("schedulerFactoryBean");
		}
		return scheduler;
	}

	/**
	 * ���һ������
	 * �������� + ������ ȷ��һ��Trigger/JobDetail
	 * @param schedulerJob
	 */
	public static void addJob(SchedulerJob schedulerJob){
		try {
			// ��ȡTrigger
			TriggerKey triggerKey = TriggerKey.triggerKey(schedulerJob.getJobName(), schedulerJob.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			QuartzJobFactory taskClass = QuartzUtils.getJobClass(schedulerJob);
			
			// ������񲻴���
			if(trigger == null){
				// �õ���������
				JobDetail jobDetail = JobBuilder
						.newJob(taskClass.getClass())
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.build();
				jobDetail.getJobDataMap().put("schedulerJob", schedulerJob);

				//cron���ʽ���ȹ�����
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
				//���µ�cronExpression���ʽ����һ���µ�trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.withSchedule(cronScheduleBuilder)
						.build();
				// ���µ�������� ��������
				scheduler.scheduleJob(jobDetail, trigger);
			}else{ 
				// Trigger�Ѵ��ڣ���ô������Ӧ�Ķ�ʱ����
				//���ʽ���ȹ�����
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
				//���µ�cronExpression���ʽ���¹���trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.withSchedule(cronScheduleBuilder)
						.build();
				//���µ�trigger��������jobִ��
				System.out.println("addJob�е�" + scheduler);
				scheduler.rescheduleJob(triggerKey, trigger);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param schedulerJob  �������
	 * @param businessObject ����ҵ�����
	 */
	public static void addJob(SchedulerJob schedulerJob,String businessId){
		try {
			// ��ȡTrigger
			TriggerKey triggerKey = TriggerKey.triggerKey(schedulerJob.getJobName(), schedulerJob.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			QuartzJobFactory taskClass = QuartzUtils.getJobClass(schedulerJob);
			
			// ������񲻴���
			if(trigger == null){
				// �õ���������
				JobDetail jobDetail = JobBuilder
						.newJob(taskClass.getClass())
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.build();
				jobDetail.getJobDataMap().put("schedulerJob", schedulerJob);
				jobDetail.getJobDataMap().put("businessId", businessId);

				//cron���ʽ���ȹ�����
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
				//���µ�cronExpression���ʽ����һ���µ�trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.withSchedule(cronScheduleBuilder)
						.build();
				// ���µ�������� ��������
				scheduler.scheduleJob(jobDetail, trigger);
			}else{ 
				// Trigger�Ѵ��ڣ���ô������Ӧ�Ķ�ʱ����
				//���ʽ���ȹ�����
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
				//���µ�cronExpression���ʽ���¹���trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.withSchedule(cronScheduleBuilder)
						.build();
				//���µ�trigger��������jobִ��
				System.out.println("addJob�е�" + scheduler);
				scheduler.rescheduleJob(triggerKey, trigger);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �������������
	 */
	public static void startScheduler(){
		if(scheduler != null){
			try {
				scheduler.start();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ����ȫ�����õ���Ӧ������
	 * @param schedulerJob
	 * @return 
	 * QuartzJobFactory ����ҵ����ĸ��࣬Job������
	 */
	public static QuartzJobFactory getJobClass(SchedulerJob schedulerJob){
		String className = schedulerJob.getClassName();
		QuartzJobFactory job = null;
		try {
			job = (QuartzJobFactory) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return job;
	}
	
	public static Object getBusinessClass(String string){
		Object o = null;
		try {
			o = (Object) Class.forName(string).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	/**
	 * �õ�����"����"���е�Job
	 * �˷�������ȫ����
	 * TODO
	 * @return
	 */
	public static List<SchedulerJob> getRunningJob(){
		List<SchedulerJob> jobList = new ArrayList<SchedulerJob>();
		System.out.println("allRunningJob�е�" + scheduler);
		try {
			// �õ�Scheduler�У���ǰ����ִ�е�Job
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			for (JobExecutionContext executingJob : executingJobs) {
				SchedulerJob schedulerJob = new SchedulerJob();
				// �õ���������
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				SchedulerJob sj =  (SchedulerJob)jobDetail.getJobDataMap().get("schedulerJob");
				// �õ�������
				Trigger trigger = executingJob.getTrigger();
				schedulerJob.setJobName(jobKey.getName());
				schedulerJob.setJobGroup(jobKey.getGroup());
				schedulerJob.setJobDesc("��������" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				schedulerJob.setJobStatus(triggerState.name());

				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					schedulerJob.setCronExpression(cronExpression);
				}
				jobList.add(schedulerJob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return jobList;
	}
	
	/**
	 * �õ����е�����
	 * @return
	 */
	public static List<SchedulerJob> getAllJob(){
		List<SchedulerJob> jobList = new ArrayList<SchedulerJob>();
		
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggersOfJob) {
					SchedulerJob job = new SchedulerJob();
					job.setJobName(jobKey.getName());
					job.setJobGroup(jobKey.getGroup());
					job.setJobDesc("��������" + trigger.getKey());
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					job.setJobStatus(triggerState.name());
					
					if(trigger instanceof CronTrigger){
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						job.setCronExpression(cronExpression);
					}
					jobList.add(job);
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobList;
	}
	
	/**
	 * ��ͣһ��Job
	 * @param schedulerJob  �������
	 */
	public static void pauseJob(SchedulerJob schedulerJob){
		try {
			JobKey jobKey = JobKey.jobKey(schedulerJob.getJobName(), schedulerJob.getJobGroup());
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ָ�һ��Job
	 * @param schedulerJob  �������
	 */
	public static void resumeJob(SchedulerJob schedulerJob){
		try {
			JobKey jobKey = JobKey.jobKey(schedulerJob.getJobName(), schedulerJob.getJobGroup());
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ��һ��Job
	 * @param schedulerJob  �������
	 */
	public static void deleteJob(SchedulerJob schedulerJob){
		try {
			JobKey jobKey = JobKey.jobKey(schedulerJob.getJobName(), schedulerJob.getJobGroup());
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����ִ��һ��Job
	 * @param schedulerJob
	 */
	public static void runAJobNow(SchedulerJob schedulerJob){
		try {
			JobKey jobKey = JobKey.jobKey(schedulerJob.getJobName(), schedulerJob.getJobGroup());
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����Job��CronExpression
	 * @param schedulerJob
	 */
	public static void updateJobCronExpression(SchedulerJob schedulerJob){
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(schedulerJob.getJobName(), schedulerJob.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
			CronTrigger newTrigger = trigger.getTriggerBuilder()
					.withIdentity(triggerKey)
					.withSchedule(cronScheduleBuilder)
					.build();
			
			scheduler.rescheduleJob(triggerKey, newTrigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
