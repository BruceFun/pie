package com.pie.ie.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.pie.commons.MockServiceCase;
import com.pie.commons.QueryPageBase;
import com.pie.utils.ResultDataFormat;

public class IeServiceTest extends MockServiceCase{
	@Autowired
	private IIeService ieService;
	
	@Test
	public void testGetPagePIEDetails() throws Exception{
		ResultDataFormat pagePIEDetails = ieService.getPagePIEDetails();
		Object data = pagePIEDetails.getData();
		System.out.println(data);
	}
	
	@Test
	public void testGetPageByQuery() throws Exception{
		String month = "2";
		String startDate = "";
		String endDate = "";
		QueryPageBase queryPage = new QueryPageBase();
		ResultDataFormat pageByQuery = ieService.getPageByQuery(month, startDate, endDate, queryPage);
		System.out.println(pageByQuery);
	}
	
	@Test
	@Rollback(false)
	public void testDelete() throws Exception{
		String id = "402880a05426ea700154274103960000";
		ResultDataFormat rf = ieService.deleteById(id);
		String msg = rf.getMsg();
		System.out.println(msg);
	}
}