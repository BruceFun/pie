package com.pie.quartzJob.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pie.domain.SchedulerJob;
import com.pie.quartzJob.service.ISchedulerJobService;
import com.pie.repositories.SchedulerJobRepository;

@Service("schedulerJobService")
@Transactional
public class SchedulerJobServiceImpl implements ISchedulerJobService{
	@Autowired
	private SchedulerJobRepository schedulerJobRepository;
	
	/**
	 * ����һ������
	 */
	@Override
	public SchedulerJob save(SchedulerJob schedulerJob) {
		return schedulerJobRepository.save(schedulerJob);
	}

	/**
	 * �õ����е���������
	 */
	@Override
	public List<SchedulerJob> findAll() {
		return schedulerJobRepository.findAll();
	}

	/**
	 * ����ID,�õ�һ������
	 */
	@Override
	public SchedulerJob getOneById(String id) {
		return schedulerJobRepository.getOne(id);
	}
}
