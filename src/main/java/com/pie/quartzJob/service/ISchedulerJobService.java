package com.pie.quartzJob.service;


import java.util.List;

import com.pie.domain.SchedulerJob;

public interface ISchedulerJobService {
	/**
	 * 保存一条数据
	 * @param schedulerJob
	 * @return
	 */
	public SchedulerJob save(SchedulerJob schedulerJob);
	
	/**
	 * 得到表中的所有数据
	 * @return
	 */
	public List<SchedulerJob> findAll();
	
	/**
	 * 根据ID,得到一个对象
	 * @param id
	 * @return
	 */
	public SchedulerJob getOneById(String id);
}
