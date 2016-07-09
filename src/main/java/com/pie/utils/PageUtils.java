package com.pie.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestParam;

public class PageUtils {
	public static PageRequest buildPageRequest(int pageNumber,int pageSize,@RequestParam(value="sortField",required=false) String sortField,@RequestParam(value="sortType",required=false) String sortType){
		Sort sort = null;
		Direction sortTypeEnum = Direction.DESC;
		if(StringUtils.isNotBlank(sortType) && AppConfig.SORT_TYPE_ASC.equals(sortType.toLowerCase())){
			sortTypeEnum = Direction.ASC;
		}
		if (StringUtils.isNotBlank(sortField)) {
			sort = new Sort(sortTypeEnum, sortField);
		}
		pageNumber = pageNumber <= 0 ? 1 : pageNumber;
		pageSize = pageSize <= 0 ? AppConfig.PAGE_SIZE_DEFAULT : pageSize;
		
		return sort == null ? new PageRequest(pageNumber - 1, pageSize) : new PageRequest(pageNumber - 1, pageSize, sort);
	}
	
	public static PageRequest buildPageRequest(int pageNumber,int pageSize,@RequestParam(value="sortField",required=false) String sortField,@RequestParam(value="sortField",required=false) String sortField2,@RequestParam(value="sortType",required=false) String sortType){
		Sort sort = null;
		Direction sortTypeEnum = Direction.DESC;
		if(StringUtils.isNotBlank(sortType) && AppConfig.SORT_TYPE_ASC.equals(sortType.toLowerCase())){
			sortTypeEnum = Direction.ASC;
		}
		if (StringUtils.isNotBlank(sortField)) {
			sort = new Sort(sortTypeEnum, sortField,sortField2);
		}
		pageNumber = pageNumber <= 0 ? 1 : pageNumber;
		pageSize = pageSize <= 0 ? AppConfig.PAGE_SIZE_DEFAULT : pageSize;
		
		return sort == null ? new PageRequest(pageNumber - 1, pageSize) : new PageRequest(pageNumber - 1, pageSize, sort);
	}
}