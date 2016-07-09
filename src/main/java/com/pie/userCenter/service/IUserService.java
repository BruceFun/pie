package com.pie.userCenter.service;

import java.math.BigDecimal;

import com.pie.domain.User;


public interface IUserService {
	/**
	 * 保存一个用户
	 * @param user 用户对象
	 * @return
	 */
	public User save(User user);
	
	/**
	 * 根据ID,得到一个用户
	 * @param id
	 * @return
	 */
	public User getOneById(String id);
	
	/**
	 * 更新总金额增加
	 * @param userId
	 * @param money
	 */
	public User updateMoneyIncrease(String userId,BigDecimal money);
	
	/**
	 * 更新总金额减少
	 * @param userId
	 * @param money
	 */
	public User updateMoneyReduces(String userId,BigDecimal money);
}
