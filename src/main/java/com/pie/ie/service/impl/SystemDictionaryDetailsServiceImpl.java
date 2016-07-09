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
	 * 保存一条类型数据
	 */
	@Override
	public SystemDictionaryDetails save(SystemDictionaryDetails details) {
		return systemDictionaryDetailsRepository.save(details);
	}

	/**
	 * 根据类型ID，得到其下的所有详情
	 */
	@Override
	public ResultDataFormat getDetailsByTypeId(String typeId) {
		ResultDataFormat rf = new ResultDataFormat("查询成功", FlagEnum.SUCCESS.value());
		if(StringUtils.isBlank(typeId)){
			return rf;
		}
		List<SystemDictionaryDetails> list = systemDictionaryDetailsRepository.getDetailsByType(typeId);
		rf.setData(list);
		return rf;
	}

	/**
	 * 根据ID得到一个详细类型
	 */
	@Override
	public SystemDictionaryDetails getOneById(String detailsId) {
		return systemDictionaryDetailsRepository.getOne(detailsId);
	}
}
