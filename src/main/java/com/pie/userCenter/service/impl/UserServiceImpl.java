package com.pie.userCenter.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pie.domain.User;
import com.pie.repositories.UserRepository;
import com.pie.userCenter.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserRepository userRepository;

	/**
	 * 保存一个用户
	 */
	@Override
	public User save(User user) {
		return userRepository.save(user);		
	}
	
	/**
	 * 根据ID,得到 一个用户
	 */
	@Override
	public User getOneById(String id) {
		return userRepository.getOne(id);
	}
	
	/**
	 * 更新总金额增加
	 */
	@Override
	public User updateMoneyIncrease(String userId, BigDecimal money) {
		User user = userRepository.getOne(userId);
		user.setMoney(user.getMoney().add(money));
		return userRepository.save(user);
	}

	/**
	 * 更新总金额减少
	 */
	@Override
	public User updateMoneyReduces(String userId, BigDecimal money) {
		User user = userRepository.getOne(userId);
		user.setMoney(user.getMoney().subtract(money));
		return userRepository.save(user);
	}
}