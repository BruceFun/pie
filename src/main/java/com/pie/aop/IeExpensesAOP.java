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
		System.out.println("请大家注意，业务对象准备开始执行了……");
	}

	@After("pointCut()")
	public void after(JoinPoint joinPoint){
		System.out.println("业务对象执行完毕……");
	}
}
