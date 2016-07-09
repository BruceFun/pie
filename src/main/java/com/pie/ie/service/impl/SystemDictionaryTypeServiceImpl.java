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
	 * ����һ����������
	 */
	@Override
	public SystemDictionaryType save(SystemDictionaryType type) {
		return systemDictionaryTypeRepository.save(type);
	}

	/**
	 * �õ���������
	 */
	@Override
	public ResultDataFormat getAll() {
		ResultDataFormat rf = new ResultDataFormat("��ѯ�ɹ�", FlagEnum.SUCCESS.value());
		List<SystemDictionaryType> list = systemDictionaryTypeRepository.findAll();
		rf.setData(list);
		return rf;
	}

	/**
	 * ����ID ,�õ�һ������
	 */
	@Override
	public SystemDictionaryType getOneById(String id) {
		return systemDictionaryTypeRepository.getOne(id);
	}
	
	/**
	 * �õ����е�֧������
	 */
	@Override
	public ResultDataFormat getExpensesByType() {
		ResultDataFormat rf = new ResultDataFormat("��ѯ�ɹ�", FlagEnum.SUCCESS.value());
		List<SystemDictionaryType> list = systemDictionaryTypeRepository.getExpensesByType();
		rf.setData(list);
		return rf;
	}
	
	/**
	 * �õ����е���������
	 */
	@Override
	public ResultDataFormat getIncomeByType() {
		ResultDataFormat rf = new ResultDataFormat("��ѯ�ɹ�", FlagEnum.SUCCESS.value());
		List<SystemDictionaryType> list = systemDictionaryTypeRepository.getIncomeByType();
		rf.setData(list);
		return rf;
	}
}
