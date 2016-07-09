package com.pie.ie.web;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pie.commons.MockMvcCase;

public class UserControllerTest extends MockMvcCase{

	@Test
	@Rollback(false)
	public void testGetUserById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/userList"));
	}
	
	@Test
	@Rollback(false)
	public void testFindByPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/findByPage"));
	}
}
