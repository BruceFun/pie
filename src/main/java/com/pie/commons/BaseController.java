package com.pie.commons;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	
	public Map<String, Integer> caculatePage(Integer pageNumber){
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer startPage = pageNumber - 5 > 0 ? pageNumber - 5 : 1;
		Integer endPage = pageNumber + 5;
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}
}
