package com.pie.quartzJob.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pie.domain.SchedulerJob;
import com.pie.quartzJob.service.ISchedulerJobService;
import com.pie.userCenter.service.IUserService;
import com.pie.utils.AppUtils;
import com.pie.utils.FlagEnum;
import com.pie.utils.QuartzUtils;
import com.pie.utils.ResultDataFormat;

@Controller
@RequestMapping("schedulerJob")
public class SchedulerJobController {
	@Autowired
	private ISchedulerJobService schedulerJobService;
	@Autowired
	private IUserService userService;
	
	/**
	 * Quartz��ҳ����
	 * @return
	 */
	@RequestMapping("quartz")
	public String index(){
		return "pages/quartz/quartzIndex";
	}

	/**
	 * �������ݿ��е�����Job
	 * @return
	 */
	@RequestMapping("runAllJob")
	@ResponseBody
	public Map<String, Object> runTest(){
		ResultDataFormat rf = new ResultDataFormat("���ж�ʱ�������гɹ�����", FlagEnum.SUCCESS.value());
		List<SchedulerJob> findAll = schedulerJobService.findAll();
		for (SchedulerJob schedulerJob : findAll) {
			if(schedulerJob.getBusinessId() != null){
				QuartzUtils.addJob(schedulerJob,schedulerJob.getBusinessId());
			}else{
				QuartzUtils.addJob(schedulerJob);
			}
		}
		QuartzUtils.startScheduler();
		
		return rf.convertResultData();
	}
	
	/**
	 * �õ����е�Job
	 * @return
	 */
	@RequestMapping("getAllJob")
	@ResponseBody
	public Map<String, Object> runList(){
		ResultDataFormat rf = new ResultDataFormat("��ѯ���ж�ʱ������ɡ���", FlagEnum.SUCCESS.value());
		List<SchedulerJob> jobList = QuartzUtils.getAllJob();
		rf.setData(jobList);
		
		return rf.convertResultData();
	}
	
	/**
	 * ����Job��id,��ͣһ��Job
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("pauseJob")
	@ResponseBody
	public Map<String, Object> pauseJob(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("��ͣ����ɹ�����", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = schedulerJobService.getOneById(id);
		QuartzUtils.pauseJob(schedulerJob);
		return rf.convertResultData();
	}
	
	/**
	 * ����Job��id,�ָ�һ��Job
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("resumeJob")
	@ResponseBody
	public Map<String, Object> resumeJob(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("�ָ�����ɹ�����", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = schedulerJobService.getOneById(id);
		QuartzUtils.resumeJob(schedulerJob);
		return rf.convertResultData();
	}
	
	/**
	 * ����Job��id,ɾ��һ��Job
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("deleteJob")
	@ResponseBody
	public Map<String, Object> deleteJob(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("ɾ������ɹ�����", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = schedulerJobService.getOneById(id);
		QuartzUtils.deleteJob(schedulerJob);
		return rf.convertResultData();
	}
	
	/**
	 * ����Job��id,����ִ��һ��Job
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("runAJobNow")
	@ResponseBody
	public Map<String, Object> runAJobNow(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("����ִ������ɹ�����", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = schedulerJobService.getOneById(id);
		QuartzUtils.runAJobNow(schedulerJob);
		return rf.convertResultData();
	}
	
	/**
	 * ����û����������
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("userBusiness")
	@ResponseBody
	public Map<String, Object> userBusiness(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("�û���ҵ����ԡ���", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = new SchedulerJob();
		schedulerJob.setId(AppUtils.getUUID());
		schedulerJob.setClassName("com.pie.quartzJob.Job_test1");
		schedulerJob.setCronExpression("0/10 * * * * ?");
		schedulerJob.setJobDesc("�û����������");
		schedulerJob.setJobGroup("����һ");
		schedulerJob.setJobName("�û������");
		schedulerJob.setJobStatus("1");
		schedulerJob.setBusinessId(id);
		schedulerJobService.save(schedulerJob);
		
		QuartzUtils.addJob(schedulerJob, id);
		return rf.convertResultData();
	}
	
	
}
