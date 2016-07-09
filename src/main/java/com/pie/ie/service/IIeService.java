package com.pie.ie.service;

import com.pie.commons.QueryPageBase;
import com.pie.domain.ItemDetails;
import com.pie.utils.ResultDataFormat;
import com.pie.vo.ItemDetailsString;


public interface IIeService {

	/**
	 * ����֧����ϸ
	 * @param itemDetailsString
	 * @return
	 */
	ResultDataFormat saveExpenses(ItemDetailsString itemDetailsString);

	/**
	 * ����������ϸ
	 * @param itemDetailsString
	 * @return
	 */
	ResultDataFormat saveIncome(ItemDetailsString itemDetailsString);

	/**
	 * �õ�����֧����������ϸ
	 * @return
	 */
	ResultDataFormat getPagePIEDetails();

	/**
	 * ����ID��ɾ��һ����¼
	 * @param id
	 * @return
	 */
	ResultDataFormat deleteById(String id);

	/**
	 * ���������õ���ҳ
	 * @param queryPage
	 * @return
	 */
	ResultDataFormat getPageByQuery(String month, String startDate, String endDate, QueryPageBase queryPage);
	
	/**
	 * ����������
	 * @return
	 */
	ItemDetails setYearMonthDayValue(String date, ItemDetails itemDetails);

	/**
	 * ����ID,�õ� һ������
	 * @param id
	 * @return ���ض���
	 */
	ItemDetails getOneId(String id);

	/**
	 * ����ID,�õ�һ������
	 * @param id
	 * @return  ���� ResultDataFormat
	 */
	ResultDataFormat getOneById(String id);

	/**
	 * �༭�󱣴�
	 * @param itemDetailsString
	 * @return
	 */
	ResultDataFormat editSave(ItemDetailsString itemDetailsString);

	

}
