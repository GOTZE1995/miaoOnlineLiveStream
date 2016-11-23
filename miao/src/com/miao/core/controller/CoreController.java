package com.miao.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 后台核心控制器
 * @author Jupiter
 *
 */
@Controller
@RequestMapping("sys")
public class CoreController {

	/**
	 * 跳转到首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "WEB-INF/index";
	}

	@RequestMapping("left")
	public String leftUI() {
		return "WEB-INF/detail/left";
	}

	@RequestMapping("top")
	public String topUI() {
		return "WEB-INF/detail/top";
	}

	@RequestMapping("bottom")
	public String bottomUI() {
		return "WEB-INF/detail/bottom";
	}

	@RequestMapping("right")
	public String rightUI() {
		return "WEB-INF/detail/right";
	}
}
