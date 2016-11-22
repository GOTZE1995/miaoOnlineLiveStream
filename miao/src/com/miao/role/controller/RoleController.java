package com.miao.role.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miao.core.utils.Page;
import com.miao.entity.Role;
import com.miao.role.service.RoleService;

/**
 * 角色控制器
 * @author 程菊飞
 * 2016/11/17
 */
@Controller
@RequestMapping("role")
public class RoleController {
	
	@Resource
	private RoleService roleService;
	
	/**
	 * 权限列表界面+查询功能
	 * @author 程菊飞
	 * @param request
	 * @return jsp页面
	 * 2016/11/17
	 */
	@RequestMapping("/listUI")
	public String listUI(@RequestParam(required=false,defaultValue="1") Integer currentPage , HttpServletRequest request,
			String searchName){
		Page<Role> page ;
		//用户输入了搜索数据
		if (searchName !=null &&  !"".equals(searchName)) {
			List<Role> list = roleService.doSearch(searchName);
			page = roleService.createPage(list, currentPage, 8);
			request.setAttribute("searchName", searchName);
		}
		//用户没有输入搜索数据
		else{
			page = roleService.createPage(null, currentPage, 8);
		}
		request.setAttribute("page", page);
		request.setAttribute("roleList", roleService.findAll());
		return "WEB-INF/detail/listRole";
	}
	
	/**
	 * 权限更新界面
	 * @author 程菊飞
	 * @param id
	 * @param request
	 * @return jsp页面
	 * 2016/11/17
	 */
	@RequestMapping("/updateUI")
	public String updateUI(Integer id ,HttpServletRequest request){
		if(id != null){
			request.setAttribute("roleInfo", roleService.findById(id));
		}
		return "WEB-INF/detail/updateRole";
	}
	
	/**
	 * 权限更新操作
	 * @author 程菊飞
	 * @param role
	 * @return
	 * 2016/11/17
	 */
	@RequestMapping("/update")
	public String update(Role role){
		if(role != null && role.getRoleId() != null){
			roleService.update(role);
		}
		return "redirect:/role/listUI.do";
	}
	
	/**
	 * 权限添加界面
	 * @author 程菊飞
	 * @return
	 * 2016/11/17
	 */
	@RequestMapping("/addUI")
	public String addUI() {
		return "WEB-INF/detail/saveRole";
	}
	
	/**
	 * 权限增加操作
	 * @author 程菊飞
	 * @param role
	 * @return
	 * 2016/11/17
	 */
	@RequestMapping("/add")
	public String add(Role role){
		if(role != null){
			roleService.save(role);
			System.out.println(role.getRoleName());
		}
		return "redirect:/role/listUI.do";
	}
	
	/**
	 * 权限删除操作
	 * @author 程菊飞
	 * @param id
	 * @return
	 * 2016/11/17
	 */
	@RequestMapping("/delete")
	public String delete(Integer id){
		try{
			if(id != null){
				roleService.deleteById(id);
			}
		}catch(Exception e){
			//删除失败，事务自动回滚，跳转到list页面
			return "redirect:/role/listUI.do";
		}
		return "redirect:/role/listUI.do";
	}
}










