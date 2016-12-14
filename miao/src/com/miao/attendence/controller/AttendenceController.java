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
import com.miao.user.service.UserService;

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
		//获取当前的班级
		User teacher=(User) session.getAttribute("user");
		String className=teacher.getClassName();
		//获取当前班级的所有同学,存入session中
		List<User> users = attendenceService.findAllStudentsByClassName(className);
		for(int i=0;i<users.size();i++){
			System.out.println("学生"+i+"的名字"+users.get(i).getUserName());
		}
		
		session.setAttribute("stus", users);
	}
	
	@RequestMapping("/getStudent")
	public void getStudent(HttpServletResponse response,HttpSession session){
		List<User> users=(List<User>) session.getAttribute("stus");
		int n=users.size();
		int k=(int) Math.floor(Math.random()*n);
		try{
			String userName=users.get(k).getUserName();
			String headImg=users.get(k).getHeadImg();
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
	
	

