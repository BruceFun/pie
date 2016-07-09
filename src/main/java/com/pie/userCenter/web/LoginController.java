package com.pie.userCenter.web;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pie.domain.User;
import com.pie.utils.AppUtils;

@Controller
@RequestMapping("userLogin")
public class LoginController {
	
	/**
	 * ���ʵ�½ҳ��
	 * @return
	 */
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	/**
	 * �����½ʱ������û��������Ƿ���ȷ
	 * @param user
	 * @return
	 */
	@RequestMapping("checkLogin")
	public String checkLogin(User user){
		if(user == null){
			return "redirect:/userLogin/login";
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		token.setRememberMe(true);
		
		AppUtils.getSubject().login(token);
		return "redirect:/main/index";
	}
}