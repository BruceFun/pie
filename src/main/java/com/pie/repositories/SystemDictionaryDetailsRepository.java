package com.pie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.pie.domain.SystemDictionaryDetails;

public interface SystemDictionaryDetailsRepository extends IBaseRepository<SystemDictionaryDetails, String> {

	@Query("select sdd from SystemDictionaryDetails sdd where sdd.type.id = ?1")
	List<SystemDictionaryDetails> getDetailsByType(String typeId);
}
