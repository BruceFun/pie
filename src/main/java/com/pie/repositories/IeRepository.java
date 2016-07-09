package com.pie.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.pie.domain.ItemDetails;

public interface IeRepository extends IBaseRepository<ItemDetails, String> {
	
	/**
	 * 原生分页，无条件的
	 * @param pageable
	 * @return
	 */
	@Query("select id from ItemDetails id")
	public Page<ItemDetails> getPagePIEDetails(Pageable pageable);
}
