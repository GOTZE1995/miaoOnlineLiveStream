package com.miao.core.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miao.entity.User;
import com.miao.user.service.UserService;

/**
 * 后台系统控制器
 * 
 * @author Jupiter
 *
 */
@Controller
@RequestMapping("sys")
public class CoreController {

	@Resource
	private UserService userService;

	/**
	 * 跳转到后台首页
	 */
	@RequestMapping("/index")
	public String index() {
		return "WEB-INF/index";
	}

	/**
	 * 后台系统左侧菜单栏
	 */
	@RequestMapping("left")
	public String leftUI() {
		return "WEB-INF/detail/left";
	}

	/**
	 * 后台系统顶部
	 */
	@RequestMapping("top")
	public String topUI() {
		return "WEB-INF/detail/top";
	}

	/**
	 * 后台系统底部
	 */
	@RequestMapping("bottom")
	public String bottomUI() {
		return "WEB-INF/detail/bottom";
	}

	/**
	 * 后台系统登录
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("login_back")
	public String login_back(User user, HttpSession session) {
		user = userService.login(user.getUserName(), user.getPassword());

		// 数据库中查到用户，将用户存到session域中
		if (user != null) {
			session.setAttribute("user", user);
			return "WEB-INF/detail/right";
		} else {
			return "WEB-INF/detail/login_back";
		}
	}

	/**
	 * 后台系统注销
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("logout_back")
	public String logout_back(HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (session != null) {
			session.removeAttribute("user");
		}
		return "redirect:/sys/login_back.do";
	}
}
