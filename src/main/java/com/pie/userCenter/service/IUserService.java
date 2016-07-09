package com.pie.userCenter.service;

import java.math.BigDecimal;

import com.pie.domain.User;


public interface IUserService {
	/**
	 * ����һ���û�
	 * @param user �û�����
	 * @return
	 */
	public User save(User user);
	
	/**
	 * ����ID,�õ�һ���û�
	 * @param id
	 * @return
	 */
	public User getOneById(String id);
	
	/**
	 * �����ܽ������
	 * @param userId
	 * @param money
	 */
	public User updateMoneyIncrease(String userId,BigDecimal money);
	
	/**
	 * �����ܽ�����
	 * @param userId
	 * @param money
	 */
	public User updateMoneyReduces(String userId,BigDecimal money);
}
