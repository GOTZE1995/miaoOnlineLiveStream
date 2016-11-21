package com.miao.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys")
public class CoreController {

	@RequestMapping("/index")
	public String index(){
		System.out.println("进来sys");
		return "WEB-INF/index";
	}
	@RequestMapping("left")
	public String leftUI(){
		return "WEB-INF/detail/left";
	}
	@RequestMapping("top")
	public String topUI(){
		return "WEB-INF/detail/top";
	}
	@RequestMapping("bottom")
	public String bottomUI(){
		return "WEB-INF/detail/bottom";
	}
	@RequestMapping("right")
	public String rightUI(){
		return "WEB-INF/detail/right";
	}
}
