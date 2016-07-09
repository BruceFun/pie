package com.pie.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class IeExpensesAOP{
//	@Pointcut("execution(* com.pie.*.service..*.*(..))")
	@Pointcut("execution(* com.pie.ie.service..*.saveExpenses(..))")
	public void pointCut(){
		
	}
	
	@Before("pointCut()")
	public void before(JoinPoint joinPoint){
		System.out.println("����ע�⣬ҵ�����׼����ʼִ���ˡ���");
	}

	@After("pointCut()")
	public void after(JoinPoint joinPoint){
		System.out.println("ҵ�����ִ����ϡ���");
	}
}