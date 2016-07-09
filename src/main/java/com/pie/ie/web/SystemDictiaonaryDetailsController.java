package com.pie.ie.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pie.ie.service.ISystemDictionaryDetailsService;

@Controller
@RequestMapping("systemDictionaryDetails")
public class SystemDictiaonaryDetailsController {
	@Autowired
	private ISystemDictionaryDetailsService systemDictionaryDetailsService;
	
	@RequestMapping("getDetailsByTypeId")
	@ResponseBody
	public Map<String, Object> getDetailsByTypeId(String typeId){
		Map<String, Object> mpaMap =  systemDictionaryDetailsService.getDetailsByTypeId(typeId).convertResultData();
		return mpaMap;
	}
}
