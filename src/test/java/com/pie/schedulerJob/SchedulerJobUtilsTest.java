package com.pie.schedulerJob;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.pie.commons.MockServiceCase;
import com.pie.domain.SchedulerJob;
import com.pie.quartzJob.service.ISchedulerJobService;
import com.pie.utils.AppUtils;
import com.pie.utils.QuartzUtils;

public class SchedulerJobUtilsTest extends MockServiceCase{
	@Autowired
	private ISchedulerJobService schedulerJobService;

	@Test
	@Rollback(false)
	public void testRun(){
		List<SchedulerJob> findAll = schedulerJobService.findAll();
		for (SchedulerJob schedulerJob : findAll) {
			QuartzUtils.addJob(schedulerJob);
		}
		QuartzUtils.startScheduler();
	}

	@Test
	@Rollback(false)
	public void testSave(){
		SchedulerJob schedulerJob = new SchedulerJob();
		schedulerJob.setId(AppUtils.getUUID());
		schedulerJob.setJobName("任务三：");
		schedulerJob.setJobGroup("测试组");
		schedulerJob.setJobStatus("1");
		schedulerJob.setCronExpression("0/5 * * * * ?");
		schedulerJob.setJobDesc("定时查询库存量");
		schedulerJobService.save(schedulerJob);
	}
}
