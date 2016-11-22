package com.miao.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.miao.core.utils.Page;
import com.miao.entity.User;
import com.miao.user.service.UserService;

/**
 * 用户控制器
 * 实现用户登录，注册，用户信息增删改查
 * @author songyulong/sunlanyun/chengjufei/fengxin
 * 2016/11/15
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 登录方法
	 * @param u 用户
	 * @param session  session域对象
	 * @return index页面
	 * @author 孙兰云
	 */
	@RequestMapping("login")
	public String login(User u,HttpSession session){
		u = userService.login(u.getUserName(),u.getPassword());
		
		//数据库中查到用户，将用户存到session域中
		if(u!=null){
			session.setAttribute("myName", u.getUserName()); 
			session.setAttribute("user", u);
			LogFactory.getLog(getClass()).info("用户"+u.getUserName()+"登录了");
		}
		return "index";
	}
	
	/**
	 * 注销
	 * @param request request域对象
	 * @return index页面
	 * @author 孙兰云
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		if(session!=null){
			session.removeAttribute("myName");
			session.removeAttribute("user");
		}
		return "index";
	}
	
	/**
	 * 用户注册
	 * @param user 表单数据
	 * @param session session域对象
	 * @return index页面
	 * @author 程菊飞
	 */
	@RequestMapping("regist")
	public String regist(User user,HttpSession session){
		userService.regist(user);
		return login(user, session);
	}
	
	/**
	 * 检查注册时用户名是否重复
	 * @param username
	 * @param response
	 * @author 程菊飞
	 */
	@RequestMapping("/checkUsername")
	public void checkUsername(String username,HttpServletResponse response){
		try {
			String result = "pass";
			//查询重复的用户名
			List<User> users = userService.findAllUsersByName(username);
			if(users!=null&&users.size()>0){
				result="repeat";
			}
			//向页面返回数据
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 检查登录时用户名与密码是否匹配
	 * @param username
	 * @param password
	 * @param response
	 * @author 孙兰云
	 */
	@RequestMapping("/loginCheckUserNameAndPwd")
	public void loginCheckUserNameAndPwd(String username,String password,HttpServletResponse response){
		try {
			String result = " not pass";
			User u = userService.login(username,password);
			
			//数据库中查到用户，将用户存到session域中
			if(u!=null){
				result="pass";
			}
		
			//向页面返回数据
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 检测注册时邮箱是否重复
	 * @param email 邮箱
	 * @param response
	 * @author 程菊飞
	 */
	@RequestMapping("/checkEmail")
	public void checkEmail(String email,HttpServletResponse response){
		try{
			String result = "pass";
			
			//查询重复的用户名
			List<User> users = userService.findAllUsersByEmail(email);
			
			if(users!=null && users.size()>0){
				result = "repeat";
			}
			
			//向页面返回数据
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 后台用户列表界面+查询功能
	 * @author 宋禹龙
	 * @param request
	 * @return jsp页面
	 * 2016/11/16
	 */
	@RequestMapping("/listUI")
	public String listUI(@RequestParam(required=false,defaultValue="1") Integer currentPage , HttpServletRequest request,
			String searchName){
		Page<User> page ;
		//用户输入了搜索数据
		if (searchName !=null &&  !"".equals(searchName)) {
			List<User> list = userService.doSearch(searchName);
			page = userService.createPage(list, currentPage, 8);
			request.setAttribute("searchName", searchName);
		}
		//用户没有输入搜索数据
		else{
			page = userService.createPage(null, currentPage, 8);
		}
		request.setAttribute("page", page);
		request.setAttribute("userList", userService.findAll());
		return "WEB-INF/detail/listUser";
	}
	
	/**
	 * 用户更新界面
	 * @param id
	 * @param request
	 * @return jsp页面
	 * 2016/11/16
	 */
	@RequestMapping("/updateUI")
	public String updateUI(Integer id,HttpServletRequest request){
		if (id != null){
			//在request中设置要回显的数据
			request.setAttribute("userInfo", userService.findById(id));
		}
		return "WEB-INF/detail/updateUser";
	}
	
	/**
	 * 用户更新操作
	 * @author 宋禹龙
	 * @param user
	 * @return jsp页面
	 * 2016/11/16
	 */
	@RequestMapping("/update")
	public String update(User user,@RequestParam(name="file",required=false)CommonsMultipartFile file,HttpServletRequest request){
		try {
			if (user!=null&&user.getId()!= null) {
				if (!file.isEmpty()) {
					//获得文件名并重命名
					String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
					//获取完整路径
					String filePath = request.getSession().getServletContext().getRealPath("/upload")+"\\"+fileName;
					//保存头像到upload目录
					file.transferTo(new File(filePath));
					//将头像相对路径设置到user中
					user.setHeadImg("upload/"+fileName);
				}
				userService.update(user);
			}
		} catch (IllegalStateException | IOException e) {
			LogFactory.getLog(getClass()).equals("头像保存失败："+e.getMessage());
			throw new RuntimeException(e);
		}
		return "redirect:/user/listUI.do";
	}
	
	/**
	 * 用户添加页面
	 * @author 宋禹龙
	 * @return jsp页面
	 * 2016/11/16
	 */
	@RequestMapping("/addUI")
	public String addUI(){
		return "WEB-INF/detail/saveUser";
	}
	
	/**
	 * 用户添加操作
	 * @author 宋禹龙
	 * @param user
	 * @return jsp页面
	 * 2016/11/16
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(User user,@RequestParam(name="file")CommonsMultipartFile file,HttpServletRequest request){
		try {
			if (user != null) {
				if (!file.isEmpty()) {
					//获得文件名并重命名
					String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
					//获取完整路径
					String filePath = request.getSession().getServletContext().getRealPath("/upload")+"\\"+fileName;
					//把文件保存到upload目录
					file.transferTo(new File(filePath));
					//头像路径保存到user
					user.setHeadImg("upload/"+fileName);
				}
				userService.save(user);
			}
		} catch (IllegalStateException | IOException e) {
			LogFactory.getLog(getClass()).equals("头像保存失败："+e.getMessage());
			throw new RuntimeException(e);
		}
		return "redirect:/user/listUI.do";
	}
	
	/**
	 * 用户删除操作
	 * @author 宋禹龙
	 * @param id
	 * @return jsp页面
	 * 2016/11/16
	 */
	@RequestMapping("/delete")
	public String delete(Integer id){
		try{
			if(id != null){
				userService.deleteById(id);
			}
		}catch(Exception e){
			//删除失败，事务自动回滚，跳转到list页面
			return "redirect:/user/listUI.do";
		}
		return "redirect:/user/listUI.do";
	}
}











