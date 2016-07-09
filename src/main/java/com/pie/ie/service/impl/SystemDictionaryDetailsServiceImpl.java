package com.pie.ie.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pie.domain.SystemDictionaryDetails;
import com.pie.ie.service.ISystemDictionaryDetailsService;
import com.pie.repositories.SystemDictionaryDetailsRepository;
import com.pie.utils.FlagEnum;
import com.pie.utils.ResultDataFormat;

@Service("systemDictionaryDetailsService")
@Transactional
public class SystemDictionaryDetailsServiceImpl implements ISystemDictionaryDetailsService{
	@Autowired
	private SystemDictionaryDetailsRepository systemDictionaryDetailsRepository;

	/**
	 * ����һ����������
	 */
	@Override
	public SystemDictionaryDetails save(SystemDictionaryDetails details) {
		return systemDictionaryDetailsRepository.save(details);
	}

	/**
	 * ��������ID���õ����µ���������
	 */
	@Override
	public ResultDataFormat getDetailsByTypeId(String typeId) {
		ResultDataFormat rf = new ResultDataFormat("��ѯ�ɹ�", FlagEnum.SUCCESS.value());
		if(StringUtils.isBlank(typeId)){
			return rf;
		}
		List<SystemDictionaryDetails> list = systemDictionaryDetailsRepository.getDetailsByType(typeId);
		rf.setData(list);
		return rf;
	}

	/**
	 * ����ID�õ�һ����ϸ����
	 */
	@Override
	public SystemDictionaryDetails getOneById(String detailsId) {
		return systemDictionaryDetailsRepository.getOne(detailsId);
	}
}
