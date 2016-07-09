package com.pie.quartzJob.service;


import java.util.List;

import com.pie.domain.SchedulerJob;

public interface ISchedulerJobService {
	/**
	 * ����һ������
	 * @param schedulerJob
	 * @return
	 */
	public SchedulerJob save(SchedulerJob schedulerJob);
	
	/**
	 * �õ����е���������
	 * @return
	 */
	public List<SchedulerJob> findAll();
	
	/**
	 * ����ID,�õ�һ������
	 * @param id
	 * @return
	 */
	public SchedulerJob getOneById(String id);
}
