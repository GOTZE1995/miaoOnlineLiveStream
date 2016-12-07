package com.miao.attendence.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miao.attendence.service.AttendenceService;
import com.miao.entity.User;

/**
 * 电子点名
 * @author 程菊飞
 * 2016/11/30
 */
@Controller
@RequestMapping("attendence")
public class AttendenceController {
	@Resource
	private AttendenceService attendenceService;
	
	@RequestMapping("/checkAttendentce")
	public void checkAttendence(HttpSession session){
		//获取当前班级的所有同学,存入session中
		List<User> users = attendenceService.findAllStudents();
		session.setAttribute("stus", users);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getStudent")
	public void getStudent(String k, HttpServletResponse response,HttpSession session){
		int i=Integer.parseInt(k);
		try{
			List<User> users=(List<User>) session.getAttribute("stus");
			String userName=users.get(i).getUserName();
			String headImg=users.get(i).getHeadImg();
			String result=userName+"|"+headImg;
			//向页面返回用户名和头像
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
	
	

