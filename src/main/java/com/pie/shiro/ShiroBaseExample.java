package com.pie.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Assert;
import org.junit.Test;

public class ShiroBaseExample {
	@Test
	public void testBaseExample(){
		// 1����ȡ SecurityManager �������˴�ʹ�� Ini �����ļ���ʼ�� SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory =
				new IniSecurityManagerFactory("classpath:shiro.ini");

		// 2���õ�SecurityManagerʵ������SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3���õ� Subject �������û���/���������֤ Token�����û����/ƾ֤��
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		
		try {
			//4����¼���������֤
			subject.login(token);
			System.out.println("��½�ɹ�����");
		} catch (AuthenticationException e) {
			//5�������֤ʧ��
			System.out.println("�����֤ʧ�ܡ���");
		}
		Assert.assertEquals(true, subject.isAuthenticated()); //�����û��Ѿ���¼
		//6���˳�
		subject.logout();

	}
}
