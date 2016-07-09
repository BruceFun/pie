package com.pie.ie.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pie.domain.SystemDictionaryType;
import com.pie.ie.service.ISystemDictionaryTypeService;
import com.pie.repositories.SystemDictionaryTypeRepository;
import com.pie.utils.FlagEnum;
import com.pie.utils.ResultDataFormat;

@Service("systemDictionaryTypeService")
@Transactional
public class SystemDictionaryTypeServiceImpl implements ISystemDictionaryTypeService{
	@Autowired
	private SystemDictionaryTypeRepository systemDictionaryTypeRepository;
	
	/**
	 * 保存一条类型数据
	 */
	@Override
	public SystemDictionaryType save(SystemDictionaryType type) {
		return systemDictionaryTypeRepository.save(type);
	}

	/**
	 * 得到所有类型
	 */
	@Override
	public ResultDataFormat getAll() {
		ResultDataFormat rf = new ResultDataFormat("查询成功", FlagEnum.SUCCESS.value());
		List<SystemDictionaryType> list = systemDictionaryTypeRepository.findAll();
		rf.setData(list);
		return rf;
	}

	/**
	 * 根据ID ,得到一个对象
	 */
	@Override
	public SystemDictionaryType getOneById(String id) {
		return systemDictionaryTypeRepository.getOne(id);
	}
	
	/**
	 * 得到所有的支出类型
	 */
	@Override
	public ResultDataFormat getExpensesByType() {
		ResultDataFormat rf = new ResultDataFormat("查询成功", FlagEnum.SUCCESS.value());
		List<SystemDictionaryType> list = systemDictionaryTypeRepository.getExpensesByType();
		rf.setData(list);
		return rf;
	}
	
	/**
	 * 得到所有的收入类型
	 */
	@Override
	public ResultDataFormat getIncomeByType() {
		ResultDataFormat rf = new ResultDataFormat("查询成功", FlagEnum.SUCCESS.value());
		List<SystemDictionaryType> list = systemDictionaryTypeRepository.getIncomeByType();
		rf.setData(list);
		return rf;
	}
}
