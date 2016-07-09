package com.pie.ie.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import antlr.collections.List;

import com.pie.commons.MockServiceCase;
import com.pie.domain.User;
import com.pie.utils.AppUtils;

public class UserServiceTest extends MockServiceCase{
//	@Autowired
//	IUserService userService;
//	
//	@Test
//	public void test() throws Exception{
//		String uuid = AppUtils.getUUID();
//		System.out.println("UUID = " + uuid);
//	}
//	
//	@Test
//	@Rollback(false)
//	public void testGetUserById() throws Exception{
//		String userId = "bd433e0b9eef4209974ccfff221b132b";
//		User user = userService.getUserById(userId);
//		System.out.println("userName=" + user.getName());
//	}
//	
//	@Test
//	@Rollback(false)
//	public void testSave() throws Exception{
//		for(int i = 0; i < 100;i++){
//			User user = new User();
//			user.setName(i+""+i+i);
//			user.setPassword("000000");
//			User u = userService.save(user);
//		}
//	}
	
//	@Test
//	@Rollback(false)
//	public void testFindByPage() throws Exception{
//		User user = new User();
//		user.setName("2");
//		List<User> page = userService.findByPage(user);
//		int count = page.size();
//	}
}