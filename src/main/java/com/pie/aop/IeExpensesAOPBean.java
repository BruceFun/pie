package com.pie.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import com.pie.vo.ItemDetailsString;

@Service("ieExpensesAOPBean")
public class IeExpensesAOPBean{

	public void after(JoinPoint joinPoint,String id) throws Throwable {
		System.out.println("业务对象执行完毕……");
		System.out.println(joinPoint.getArgs().toString());
		Object[] args = joinPoint.getArgs();
		ItemDetailsString ids = (ItemDetailsString)args[0];
		System.out.println(ids.getMoney());
		System.out.println(ids.getPayTime());
		System.out.println(ids.getTypeId());
	}

	public void before(JoinPoint joinPoint)throws Throwable {
		System.out.println("请大家注意，业务对象准备开始执行了……");
		System.out.println(joinPoint.getArgs().toString());
	}
	
	public void other(JoinPoint joinPoint) throws Throwable{
		System.out.println("======================================");
		System.out.println(joinPoint.getArgs().toString());
	}
}
