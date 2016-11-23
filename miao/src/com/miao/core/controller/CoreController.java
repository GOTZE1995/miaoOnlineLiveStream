package com.miao.core.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miao.entity.User;
import com.miao.user.service.UserService;

@Controller
@RequestMapping("sys")
public class CoreController {
	
	@Resource
	private UserService userService;

	@RequestMapping("/index")
	public String index(){
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
	
	@RequestMapping("login_back")
	public String login_back(User user,HttpSession session){
        user = userService.login(user.getUserName(),user.getPassword());
		
		//数据库中查到用户，将用户存到session域中
		if(user!=null){
			session.setAttribute("user", user);
			return "WEB-INF/detail/right";
		}else{
			return "WEB-INF/detail/login_back";
		}
	}
	
	@RequestMapping("logout_back")
	public String logout_back(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		if(session!=null){
			session.removeAttribute("user");
		}
		return "redirect:/sys/login_back.do";
	}
}
