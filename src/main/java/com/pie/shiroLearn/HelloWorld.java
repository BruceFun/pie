package com.pie.shiroLearn;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import com.pie.utils.AppUtils;


public class HelloWorld {
	public static void main(String[] args) {
		Factory<org.apache.shiro.mgt.SecurityManager> f = new IniSecurityManagerFactory("classpath:shiroTest.ini");
		org.apache.shiro.mgt.SecurityManager s = f.getInstance();
		SecurityUtils.setSecurityManager(s);
		
		UsernamePasswordToken token = new UsernamePasswordToken("zs", "123");
		token.setRememberMe(true);
		
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		
		boolean flag = currentUser.isPermitted("p20");
		System.out.println("flag = " + flag);
		
		String aa = new Sha256Hash("000000").toHex(); // 16进制加密（64位）
		String bb = new Sha256Hash("000000").toBase64(); // Base6464加密（44位）
		System.out.println("sy——" + aa);
		System.out.println("ta——" + bb);
		
		System.out.println("my——" + AppUtils.getHashedCredentials("000000", "sha-256", false));
		
	}
	
}
