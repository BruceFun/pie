package com.pie.ie.service;

import com.pie.domain.SystemDictionaryDetails;
import com.pie.utils.ResultDataFormat;

public interface ISystemDictionaryDetailsService {
	/**
	 * ����һ����������
	 * @param type
	 * @return
	 */
	public SystemDictionaryDetails save(SystemDictionaryDetails details);
	
	/**
	 * ��������ID���õ����µ�������ϸ�б�
	 * @param typeId  ����ID
	 * @return
	 */
	public ResultDataFormat getDetailsByTypeId(String typeId);

	/**
	 * ����ID���õ�һ����ϸ����
	 * @param detailsId
	 * @return
	 */
	public SystemDictionaryDetails getOneById(String detailsId);
}
