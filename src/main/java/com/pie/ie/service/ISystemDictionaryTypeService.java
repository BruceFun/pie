package com.pie.ie.service;

import com.pie.domain.SystemDictionaryType;
import com.pie.utils.ResultDataFormat;

public interface ISystemDictionaryTypeService {
	/**
	 * 保存一条类型数据
	 * @param type
	 * @return
	 */
	public SystemDictionaryType save(SystemDictionaryType type);
	
	/**
	 * 根据ID ，得到一个对象
	 * @param id
	 * @return
	 */
	public SystemDictionaryType getOneById(String id);
	
	/**
	 * 得到所有的类型
	 * @return
	 */
	public ResultDataFormat getAll();

	/**
	 * 得到所有的支出类型
	 * @return
	 */
	public ResultDataFormat getExpensesByType();
	
	/**
	 * 得到所有的收入类型
	 * @return
	 */
	public ResultDataFormat getIncomeByType();
}
