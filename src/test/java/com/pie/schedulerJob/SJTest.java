package com.pie.schedulerJob;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pie.commons.MockMvcCase;

public class SJTest extends MockMvcCase{
	
	@Test
	public void runTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/schedulerJob/quartz"));
	}
}
