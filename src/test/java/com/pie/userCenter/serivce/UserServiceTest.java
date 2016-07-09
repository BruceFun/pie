package com.pie.userCenter.serivce;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pie.commons.MockServiceCase;
import com.pie.userCenter.service.IUserService;

public class UserServiceTest extends MockServiceCase{
	@Autowired
	private IUserService userService;
	
	@Test
	public void testSave() {
//		for(int i=0; i<27;i++){
//			User user = new User();
//			user.setId(AppUtils.getUUID());
//			user.setName(i+"");
//			user.setPassword(AppUtils.getEncoderByMD5("000000"));
//			user.setTime(new Date());
//			userService.save(user);
//		}
	}
}
