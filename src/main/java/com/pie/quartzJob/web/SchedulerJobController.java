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
	 * Quartz首页访问
	 * @return
	 */
	@RequestMapping("quartz")
	public String index(){
		return "pages/quartz/quartzIndex";
	}

	/**
	 * 运行数据库中的所有Job
	 * @return
	 */
	@RequestMapping("runAllJob")
	@ResponseBody
	public Map<String, Object> runTest(){
		ResultDataFormat rf = new ResultDataFormat("所有定时任务运行成功……", FlagEnum.SUCCESS.value());
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
	 * 得到所有的Job
	 * @return
	 */
	@RequestMapping("getAllJob")
	@ResponseBody
	public Map<String, Object> runList(){
		ResultDataFormat rf = new ResultDataFormat("查询所有定时任务完成……", FlagEnum.SUCCESS.value());
		List<SchedulerJob> jobList = QuartzUtils.getAllJob();
		rf.setData(jobList);
		
		return rf.convertResultData();
	}
	
	/**
	 * 根据Job的id,暂停一个Job
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("pauseJob")
	@ResponseBody
	public Map<String, Object> pauseJob(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("暂停任务成功……", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = schedulerJobService.getOneById(id);
		QuartzUtils.pauseJob(schedulerJob);
		return rf.convertResultData();
	}
	
	/**
	 * 根据Job的id,恢复一个Job
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("resumeJob")
	@ResponseBody
	public Map<String, Object> resumeJob(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("恢复任务成功……", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = schedulerJobService.getOneById(id);
		QuartzUtils.resumeJob(schedulerJob);
		return rf.convertResultData();
	}
	
	/**
	 * 根据Job的id,删除一个Job
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("deleteJob")
	@ResponseBody
	public Map<String, Object> deleteJob(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("删除任务成功……", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = schedulerJobService.getOneById(id);
		QuartzUtils.deleteJob(schedulerJob);
		return rf.convertResultData();
	}
	
	/**
	 * 根据Job的id,立即执行一个Job
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("runAJobNow")
	@ResponseBody
	public Map<String, Object> runAJobNow(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("立即执行任务成功……", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = schedulerJobService.getOneById(id);
		QuartzUtils.runAJobNow(schedulerJob);
		return rf.convertResultData();
	}
	
	/**
	 * 添加用户类任务测试
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("userBusiness")
	@ResponseBody
	public Map<String, Object> userBusiness(String id, Model model){
		ResultDataFormat rf = new ResultDataFormat("用户类业务测试……", FlagEnum.SUCCESS.value());
		SchedulerJob schedulerJob = new SchedulerJob();
		schedulerJob.setId(AppUtils.getUUID());
		schedulerJob.setClassName("com.pie.quartzJob.Job_test1");
		schedulerJob.setCronExpression("0/10 * * * * ?");
		schedulerJob.setJobDesc("用户类任务测试");
		schedulerJob.setJobGroup("任务一");
		schedulerJob.setJobName("用户类测试");
		schedulerJob.setJobStatus("1");
		schedulerJob.setBusinessId(id);
		schedulerJobService.save(schedulerJob);
		
		QuartzUtils.addJob(schedulerJob, id);
		return rf.convertResultData();
	}
	
	
}
