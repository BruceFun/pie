package com.pie.utils;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * ʹ�ÿ����л��������ȡSpring��������  
 * @author bruce_000
 *
 */
@SuppressWarnings("all")
public class SpringBeanUtils implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public <T> T getBean(Class<T> clazz,String beanName){  
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  
        return (T)context.getBean(beanName);  
    }

}
