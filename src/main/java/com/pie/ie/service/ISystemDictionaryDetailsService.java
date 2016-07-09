package com.pie.ie.service;

import com.pie.domain.SystemDictionaryDetails;
import com.pie.utils.ResultDataFormat;

public interface ISystemDictionaryDetailsService {
	/**
	 * 保存一条类型数据
	 * @param type
	 * @return
	 */
	public SystemDictionaryDetails save(SystemDictionaryDetails details);
	
	/**
	 * 根据类型ID，得到其下的所有详细列表
	 * @param typeId  类型ID
	 * @return
	 */
	public ResultDataFormat getDetailsByTypeId(String typeId);

	/**
	 * 根据ID，得到一个详细类型
	 * @param detailsId
	 * @return
	 */
	public SystemDictionaryDetails getOneById(String detailsId);
}
