package com.pie.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.pie.domain.User;

public interface UserRepository extends IBaseRepository<User, String>{

	/**
	 * �ֲ���ѯ
	 * @param user
	 * @param pageable
	 * @return
	 */
	@Query("select u from User u where u.name like ?1")
	Page<User> findByPage(String userName, Pageable pageable);
	
	/**
	 * �����û����õ�һ���û�����
	 * @param username
	 * @return
	 */
	@Query("select u from User u where u.name = ?1")
	User getUserByName(String username);
}
