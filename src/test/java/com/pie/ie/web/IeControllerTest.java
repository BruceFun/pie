package com.pie.ie.web;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pie.commons.MockMvcCase;

public class IeControllerTest extends MockMvcCase{
	@Test
	public void testEdit() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/ie/edit").param("id", "402882f15422e15f015422eaebbd0003"));
	}
	
	@Test
	public void testExpenses() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/ie/expenses"));
	}
}
