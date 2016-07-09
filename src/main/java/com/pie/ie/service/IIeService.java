package com.pie.ie.service;

import com.pie.commons.QueryPageBase;
import com.pie.domain.ItemDetails;
import com.pie.utils.ResultDataFormat;
import com.pie.vo.ItemDetailsString;


public interface IIeService {

	/**
	 * 保存支出详细
	 * @param itemDetailsString
	 * @return
	 */
	ResultDataFormat saveExpenses(ItemDetailsString itemDetailsString);

	/**
	 * 保存收入详细
	 * @param itemDetailsString
	 * @return
	 */
	ResultDataFormat saveIncome(ItemDetailsString itemDetailsString);

	/**
	 * 得到个人支出与收入明细
	 * @return
	 */
	ResultDataFormat getPagePIEDetails();

	/**
	 * 根据ID，删除一条记录
	 * @param id
	 * @return
	 */
	ResultDataFormat deleteById(String id);

	/**
	 * 根据条件得到分页
	 * @param queryPage
	 * @return
	 */
	ResultDataFormat getPageByQuery(String month, String startDate, String endDate, QueryPageBase queryPage);
	
	/**
	 * 设置年月日
	 * @return
	 */
	ItemDetails setYearMonthDayValue(String date, ItemDetails itemDetails);

	/**
	 * 根据ID,得到 一个对象
	 * @param id
	 * @return 返回对象
	 */
	ItemDetails getOneId(String id);

	/**
	 * 根据ID,得到一个对象
	 * @param id
	 * @return  返回 ResultDataFormat
	 */
	ResultDataFormat getOneById(String id);

	/**
	 * 编辑后保存
	 * @param itemDetailsString
	 * @return
	 */
	ResultDataFormat editSave(ItemDetailsString itemDetailsString);

	

}
