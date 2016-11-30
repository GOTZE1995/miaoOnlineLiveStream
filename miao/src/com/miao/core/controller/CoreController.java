package com.miao.core.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miao.entity.User;
import com.miao.user.service.UserService;

/**
 * 后台系统控制器
 * 
 * @author songyulong
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
	 * 后台系统底部
	 */
	@RequestMapping("right")
	public String rightUI() {
		return "WEB-INF/detail/right";
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
		String result=null;//用于存返回的字符串
		user = userService.login(user.getUserName(), user.getPassword());
		
		// 数据库中查到用户，将用户存到session域中
		if (user != null) {
			String roleName=user.getRole().getRoleName().trim();
			if(roleName.equals("管理员")){
				session.setAttribute("AdminUser", user);
				result= "WEB-INF/detail/right";
			}else{
				result="WEB-INF/detail/login_back";
			}
		} else {
			result="WEB-INF/detail/login_back";
		}
		return result;
	}
	
	/**
	 * 后台系统注销
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout_back")
	public void logout_back(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session != null) {
			session.removeAttribute("AdminUser");
		}
		String result = "success";
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
