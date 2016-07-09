package com.pie.ie.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.pie.commons.MockServiceCase;
import com.pie.domain.SystemDictionaryType;
import com.pie.utils.AppUtils;
import com.pie.utils.ResultDataFormat;

public class SystemDictionaryTypeTest extends MockServiceCase{
	@Autowired
	private ISystemDictionaryTypeService systemDictionaryTypeService;

	@Test
	@Rollback(false)
	public void testSave() {
//		String[] names = {"正式工作","兼职"};
//		for (int i = 0; i < names.length; i++) {
//			SystemDictionaryType type = new SystemDictionaryType();
//			type.setId(AppUtils.getUUID());
//			type.setName(names[i]);
//			type.setType("2");
//			systemDictionaryTypeService.save(type);
//		}
	}
	
	@Test
	@Rollback(false)
	public void testGetAll(){
		ResultDataFormat all = systemDictionaryTypeService.getAll();
		System.out.println(all.getData());
	}
	
	@Test
	@Rollback(false)
	public void testGetOneById(){
		SystemDictionaryType type = systemDictionaryTypeService.getOneById("402882f15405f994015405f9a2950000");
		System.out.println(type);
		System.out.println(type.getName());
		
	}
}
