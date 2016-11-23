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
import com.miao.entity.Room;
import com.miao.entity.User;
import com.miao.role.service.RoleService;
import com.miao.user.service.UserService;

/**
 * 用户控制器 实现用户登录，注册，用户信息增删改查
 * 
 * @author songyulong/sunlanyun/chengjufei/fengxin 2016/11/15
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	/**
	 * 登录方法
	 * 
	 * @param user
	 * @param session
	 * @return index页面
	 * @author 孙兰云
	 */
	@RequestMapping("login")
	public String login(User user, HttpSession session) {
		user = userService.login(user.getUserName(), user.getPassword());

		// 数据库中查到用户，将用户存到session域中
		if (user != null) {
			session.setAttribute("user", user);
			LogFactory.getLog(getClass()).info("用户" + user.getUserName() + "登录了");
		}
		return "index";
	}

	/**
	 * 注销
	 * 
	 * @param request
	 * @return index页面
	 * @author 孙兰云
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session != null) {
			session.removeAttribute("myName");
			session.removeAttribute("user");
		}
		return "index";
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @param session
	 * @return index页面
	 * @author 程菊飞
	 */
	@RequestMapping("regist")
	public String regist(User user, HttpSession session) {
		userService.regist(user);
		return login(user, session);
	}

	/**
	 * 检查注册时用户名是否重复
	 * 
	 * @param username
	 * @param response
	 * @author 程菊飞
	 */
	@RequestMapping("/checkUsername")
	public void checkUsername(String username, HttpServletResponse response) {
		try {
			String result = "pass";
			// 查询重复的用户名
			List<User> users = userService.findAllUsersByName(username);
			if (users != null && users.size() > 0) {
				result = "repeat";
			}
			// 向页面返回数据
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 检查登录时用户名与密码是否匹配
	 * 
	 * @param username
	 * @param password
	 * @param response
	 * @author 孙兰云
	 */
	@RequestMapping("/loginCheckUserNameAndPwd")
	public void loginCheckUserNameAndPwd(String username, String password, HttpServletResponse response) {
		try {
			String result = " not pass";
			User u = userService.login(username, password);

			// 数据库中查到用户，将用户存到session域中
			if (u != null) {
				result = "pass";
			}

			// 向页面返回数据
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 检测注册时邮箱是否重复
	 * 
	 * @param email
	 * @param response
	 * @author 程菊飞
	 */
	@RequestMapping("/checkEmail")
	public void checkEmail(String email, HttpServletResponse response) {
		try {
			String result = "pass";

			// 查询重复的用户名
			List<User> users = userService.findAllUsersByEmail(email);

			if (users != null && users.size() > 0) {
				result = "repeat";
			}

			// 向页面返回数据
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 后台用户列表界面+查询功能
	 * 
	 * @param request
	 * @return jsp页面 2016/11/16
	 */
	@RequestMapping("/listUI")
	public String listUI(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			HttpServletRequest request, String searchName) {
		Page<User> page;
		List<User> list = null;
		if (searchName != null && !"".equals(searchName)) {
			// 根据搜索名查询
			list = userService.doSearch(searchName);
			// 将serchName设置成当前搜索的名称
			request.setAttribute("searchName", searchName);
		}
		// 将list放到分页对象中，若没有数据，默认为第一页
		page = userService.createPage(list, currentPage, 8);

		request.setAttribute("page", page);
		request.setAttribute("userList", userService.findAll());
		return "WEB-INF/detail/listUser";
	}

	/**
	 * 用户更新界面
	 * 
	 * @param id
	 * @param request
	 * @return jsp页面 2016/11/16
	 */
	@RequestMapping("/updateUI")
	public String updateUI(Integer id, HttpServletRequest request) {
		// 添加角色列表
		request.setAttribute("roleList", roleService.findAll());
		if (id != null) {
			// 在request中设置要回显的数据
			request.setAttribute("userInfo", userService.findById(id));
		}
		return "WEB-INF/detail/updateUser";
	}

	/**
	 * 用户更新操作
	 * 
	 * @param user
	 * @return jsp页面 2016/11/16
	 */
	@RequestMapping("/update")
	public String update(User user, Integer roleId,
			@RequestParam(name = "file", required = false) CommonsMultipartFile file, HttpServletRequest request) {
		try {
			if (user != null && user.getId() != null) {
				if (roleId != null) {
					// 根据id查询角色添加到用户
					user.setRole(roleService.findById(roleId));
				}
				if (!file.isEmpty()) {
					// 获得文件名并重命名
					String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
					// 获取完整路径
					String filePath = request.getSession().getServletContext().getRealPath("/upload") + "\\" + fileName;
					// 保存头像到upload目录
					file.transferTo(new File(filePath));
					// 将头像相对路径设置到user中
					user.setHeadImg("upload/" + fileName);
				}
				userService.update(user);
			}
		} catch (IllegalStateException | IOException e) {
			LogFactory.getLog(getClass()).equals("头像保存失败：" + e.getMessage());
			throw new RuntimeException(e);
		}
		return "redirect:/user/listUI.do";
	}

	/**
	 * 用户添加页面
	 * 
	 * @return jsp页面 2016/11/16
	 */
	@RequestMapping("/addUI")
	public String addUI(HttpServletRequest request) {
		// 添加角色列表
		request.setAttribute("roleList", roleService.findAll());
		return "WEB-INF/detail/saveUser";
	}

	/**
	 * 用户添加操作
	 * 
	 * @param user
	 * @return jsp页面 2016/11/16
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(User user, Integer roleId, @RequestParam(name = "file") CommonsMultipartFile file,
			HttpServletRequest request) {
		try {
			if (user != null) {
				if (roleId != null) {
					// 根据id查询角色添加到用户
					user.setRole(roleService.findById(roleId));
				}
				if (!file.isEmpty()) {
					// 获得文件名并重命名
					String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
					// 获取完整路径
					String filePath = request.getSession().getServletContext().getRealPath("/upload") + "\\" + fileName;
					// 把文件保存到upload目录
					file.transferTo(new File(filePath));
					// 头像路径保存到user
					user.setHeadImg("upload/" + fileName);
				}
				userService.save(user);
			}
		} catch (IllegalStateException | IOException e) {
			LogFactory.getLog(getClass()).equals("头像保存失败：" + e.getMessage());
			throw new RuntimeException(e);
		}
		return "redirect:/user/listUI.do";
	}

	/**
	 * 用户删除操作
	 * 
	 * @param id
	 * @return jsp页面 2016/11/16
	 */
	@RequestMapping("/delete")
	public String delete(Integer id) {
		try {
			if (id != null) {
				userService.deleteById(id);
			}
		} catch (Exception e) {
			// 删除失败，事务自动回滚，跳转到list页面
			return "redirect:/user/listUI.do";
		}
		return "redirect:/user/listUI.do";
	}

	/**
	 * 用户信息修改
	 * 
	 * @param email
	 * @param nickName
	 * @param session
	 * @author 程菊飞
	 */
	@RequestMapping("/edit")
	public String edit(@RequestParam("email") String email, @RequestParam("nickName") String nickName,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		user.setEmail(email);
		user.setNickName(nickName);
		userService.update(user);
		return "myinfo";
	}

	/**
	 * 修改用户信息时邮箱校验
	 * 
	 * @param newEmail
	 * @param response
	 * @param session
	 * @author 程菊飞
	 */
	@RequestMapping("/checkNewEmail")
	public void checkNewEmail(String newEmail, HttpServletResponse response, HttpSession session) {
		String result = "";
		User u = (User) session.getAttribute("user");
		String oldEmail = u.getEmail();
		try {
			if (newEmail == "") {
				result = "null";
			} else if (newEmail.equals(oldEmail)) {
				result = "same";
			} else {
				List<User> users = userService.findAllUsersByEmail(newEmail);
				if (users != null && users.size() > 0) {
					result = "repeat";
				}
			}
			// 向页面返回数据
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 用户密码修改
	 * 
	 * @param newPwd
	 * @param session
	 * @author 冯鑫
	 */
	@RequestMapping("/editPwd")
	public String edit(@RequestParam("newPwd") String password, HttpSession session) {
		// 获得当前登录
		User user = (User) session.getAttribute("user");
		// 修改用户信息
		user.setPassword(password);
		userService.update(user);
		return "myinfo";
	}

	/**
	 * 修改用户密码时原密码校验
	 * 
	 * @param nowPwd
	 * @param response
	 * @param session
	 * @author 冯鑫
	 */
	@RequestMapping("/checkNowPwd")
	public void checkNowPwd(String nowPwd, HttpServletResponse response, HttpSession session) {
		String result = "";
		User u = (User) session.getAttribute("user");
		String oldPwd = u.getPassword();
		try {
			if (nowPwd == "") {
				result = "null";
			} else if (nowPwd.equals(oldPwd)) {
				result = "same";
			} else {
				result = "false";
			}
			// 向页面返回数据
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
