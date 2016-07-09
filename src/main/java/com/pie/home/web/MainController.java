package com.pie.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main")
public class MainController {
	@RequestMapping("index")
	public String index(){
		return "pages/index/index";
	}
}