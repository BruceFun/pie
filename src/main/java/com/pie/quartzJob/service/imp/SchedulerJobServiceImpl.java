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
	 * 保存一条数据
	 */
	@Override
	public SchedulerJob save(SchedulerJob schedulerJob) {
		return schedulerJobRepository.save(schedulerJob);
	}

	/**
	 * 得到表中的所有数据
	 */
	@Override
	public List<SchedulerJob> findAll() {
		return schedulerJobRepository.findAll();
	}

	/**
	 * 根据ID,得到一个对象
	 */
	@Override
	public SchedulerJob getOneById(String id) {
		return schedulerJobRepository.getOne(id);
	}
}
