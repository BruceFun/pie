package com.pie.ie.web;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pie.commons.MockMvcCase;

public class SystemTypeTest extends MockMvcCase{
	@Test
	public void testGetTypes() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/systemDictionaryDetails/getDetailsByTypeId").param("typeId", "402882f15405f994015405f9a32a0001"));
	}
}
