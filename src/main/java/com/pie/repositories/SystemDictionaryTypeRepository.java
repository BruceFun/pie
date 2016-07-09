package com.pie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.pie.domain.SystemDictionaryType;

public interface SystemDictionaryTypeRepository extends IBaseRepository<SystemDictionaryType, String> {
	/**
	 * �õ����е�֧������
	 * @return
	 */
	@Query("select sd from SystemDictionaryType sd where sd.type = '1'")
	List<SystemDictionaryType> getExpensesByType();
	
	/**
	 * �õ����е���������
	 * @return
	 */
	@Query("select sd from SystemDictionaryType sd where sd.type = '2'")
	List<SystemDictionaryType> getIncomeByType();

}
