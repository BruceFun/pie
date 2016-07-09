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
 * 由任务名+组名决定一个任务
 * @author bruce_000
 * 
 * 小提示
	更新表达式，判断表达式是否正确可用一下代码
	CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("xxxxx");
 *
 */
@SuppressWarnings("all")
public class QuartzUtils {
	// 任务调度器
	private static Scheduler scheduler;

	static{
		//schedulerFactoryBean 由spring创建注入
		ApplicationContext ctx = new ClassPathXmlApplicationContext("quartz-context.xml");  
		System.out.println(ctx);
		scheduler = (Scheduler)ctx.getBean("schedulerFactoryBean");
		System.out.println("static中的" + scheduler);

	}

	/**
	 * 得到一个 Scheduler 对象
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
	 * 添加一个任务
	 * 由任务名 + 任务组 确定一个Trigger/JobDetail
	 * @param schedulerJob
	 */
	public static void addJob(SchedulerJob schedulerJob){
		try {
			// 获取Trigger
			TriggerKey triggerKey = TriggerKey.triggerKey(schedulerJob.getJobName(), schedulerJob.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			QuartzJobFactory taskClass = QuartzUtils.getJobClass(schedulerJob);
			
			// 如果任务不存在
			if(trigger == null){
				// 得到任务详情
				JobDetail jobDetail = JobBuilder
						.newJob(taskClass.getClass())
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.build();
				jobDetail.getJobDataMap().put("schedulerJob", schedulerJob);

				//cron表达式调度构建器
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
				//按新的cronExpression表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.withSchedule(cronScheduleBuilder)
						.build();
				// 把新的任务加入 调度器中
				scheduler.scheduleJob(jobDetail, trigger);
			}else{ 
				// Trigger已存在，那么更新相应的定时设置
				//表达式调度构建器
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
				//按新的cronExpression表达式重新构建trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.withSchedule(cronScheduleBuilder)
						.build();
				//按新的trigger重新设置job执行
				System.out.println("addJob中的" + scheduler);
				scheduler.rescheduleJob(triggerKey, trigger);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param schedulerJob  任务对象
	 * @param businessObject 具体业务对象
	 */
	public static void addJob(SchedulerJob schedulerJob,String businessId){
		try {
			// 获取Trigger
			TriggerKey triggerKey = TriggerKey.triggerKey(schedulerJob.getJobName(), schedulerJob.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			QuartzJobFactory taskClass = QuartzUtils.getJobClass(schedulerJob);
			
			// 如果任务不存在
			if(trigger == null){
				// 得到任务详情
				JobDetail jobDetail = JobBuilder
						.newJob(taskClass.getClass())
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.build();
				jobDetail.getJobDataMap().put("schedulerJob", schedulerJob);
				jobDetail.getJobDataMap().put("businessId", businessId);

				//cron表达式调度构建器
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
				//按新的cronExpression表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.withSchedule(cronScheduleBuilder)
						.build();
				// 把新的任务加入 调度器中
				scheduler.scheduleJob(jobDetail, trigger);
			}else{ 
				// Trigger已存在，那么更新相应的定时设置
				//表达式调度构建器
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
				//按新的cronExpression表达式重新构建trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(schedulerJob.getJobName(), schedulerJob.getJobGroup())
						.withSchedule(cronScheduleBuilder)
						.build();
				//按新的trigger重新设置job执行
				System.out.println("addJob中的" + scheduler);
				scheduler.rescheduleJob(triggerKey, trigger);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动任务调度器
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
	 * 根据全类名得到对应的类名
	 * @param schedulerJob
	 * @return 
	 * QuartzJobFactory 所有业务类的父类，Job的子类
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
	 * 得到所有"正在"运行的Job
	 * 此方法不完全……
	 * TODO
	 * @return
	 */
	public static List<SchedulerJob> getRunningJob(){
		List<SchedulerJob> jobList = new ArrayList<SchedulerJob>();
		System.out.println("allRunningJob中的" + scheduler);
		try {
			// 得到Scheduler中，当前正在执行的Job
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			for (JobExecutionContext executingJob : executingJobs) {
				SchedulerJob schedulerJob = new SchedulerJob();
				// 得到任务详情
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				SchedulerJob sj =  (SchedulerJob)jobDetail.getJobDataMap().get("schedulerJob");
				// 得到触发器
				Trigger trigger = executingJob.getTrigger();
				schedulerJob.setJobName(jobKey.getName());
				schedulerJob.setJobGroup(jobKey.getGroup());
				schedulerJob.setJobDesc("触发器：" + trigger.getKey());
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
	 * 得到所有的任务
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
					job.setJobDesc("触发器：" + trigger.getKey());
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
	 * 暂停一个Job
	 * @param schedulerJob  任务对象
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
	 * 恢复一个Job
	 * @param schedulerJob  任务对象
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
	 * 删除一个Job
	 * @param schedulerJob  任务对象
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
	 * 立即执行一个Job
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
	 * 更新Job的CronExpression
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
