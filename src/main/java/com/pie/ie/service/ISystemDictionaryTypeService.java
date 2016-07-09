package com.pie.ie.service;

import com.pie.domain.SystemDictionaryType;
import com.pie.utils.ResultDataFormat;

public interface ISystemDictionaryTypeService {
	/**
	 * ����һ����������
	 * @param type
	 * @return
	 */
	public SystemDictionaryType save(SystemDictionaryType type);
	
	/**
	 * ����ID ���õ�һ������
	 * @param id
	 * @return
	 */
	public SystemDictionaryType getOneById(String id);
	
	/**
	 * �õ����е�����
	 * @return
	 */
	public ResultDataFormat getAll();

	/**
	 * �õ����е�֧������
	 * @return
	 */
	public ResultDataFormat getExpensesByType();
	
	/**
	 * �õ����е���������
	 * @return
	 */
	public ResultDataFormat getIncomeByType();
}
