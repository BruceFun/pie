package com.pie.ie.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.pie.commons.MockServiceCase;

public class SystemDictionaryDetailsTest extends MockServiceCase{
	@Autowired
	private ISystemDictionaryDetailsService systemDictionaryDetailsService;
	@Autowired
	private ISystemDictionaryTypeService systemDictionaryTypeService;
	
	@Test
	@Rollback(false)
	public void testSave() {
//		String[] strings = {"工作兼职","生活兼职"};
// 		SystemDictionaryType type = systemDictionaryTypeService.getOneById("402882f1541945610154194570e60001");
//		for (int i = 0; i < strings.length; i++) {
//			SystemDictionaryDetails details = new SystemDictionaryDetails();
//			details.setId(AppUtils.getUUID());
//			details.setName(strings[i]);
//			details.setType(type);
//			systemDictionaryDetailsService.save(details);
//		}
	}

}
