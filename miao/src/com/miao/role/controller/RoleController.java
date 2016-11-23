package com.miao.role.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miao.core.utils.Page;
import com.miao.entity.Power;
import com.miao.entity.Role;
import com.miao.power.service.PowerService;
import com.miao.role.service.RoleService;

/**
 * 角色控制器
 * 
 * @author 程菊飞 2016/11/17
 */
@Controller
@RequestMapping("role")
public class RoleController {

	@Resource
	private RoleService roleService;
	@Resource
	private PowerService powerService;

	/**
	 * 权限列表界面+查询功能
	 * 
	 * @author 程菊飞
	 * @param request
	 * @return jsp页面 2016/11/17
	 */
	@RequestMapping("/listUI")
	public String listUI(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			HttpServletRequest request, String searchName) {
		Page<Role> page;
		List<Role> list = null;
		if (searchName != null && !"".equals(searchName)) {
			// 根据搜索名查询
			list = roleService.doSearch(searchName);
			// 将serchName设置成当前搜索的名称
			request.setAttribute("searchName", searchName);
		}
		// 将list放到分页对象中，若没有数据，默认为第一页
		page = roleService.createPage(list, currentPage, 8);
		
		request.setAttribute("page", page);
		request.setAttribute("roleList", roleService.findAll());
		return "WEB-INF/detail/listRole";
	}

	/**
	 * 权限更新界面
	 * 
	 * @author 程菊飞
	 * @param id
	 * @param request
	 * @return jsp页面 2016/11/17
	 */
	@RequestMapping("/updateUI")
	public String updateUI(Integer id, HttpServletRequest request) {
		// 添加权限列表
		request.setAttribute("powerList", powerService.findAll());
		if (id != null) {
			// 将制定角色数据发送到页面
			request.setAttribute("roleInfo", roleService.findById(id));
		}
		return "WEB-INF/detail/updateRole";
	}

	/**
	 * 权限更新操作
	 * 
	 * @author 程菊飞
	 * @param role
	 * @return 2016/11/17
	 */
	@RequestMapping("/update")
	public String update(Role role, Integer[] powerIds) {
		if (role != null && role.getRoleId() != null) {
			// 设置一个新的set保存权限
			Set<Power> set = new HashSet<Power>();
			// 根据id查找权限
			if (powerIds != null) {
				for (Integer id : powerIds) {
					set.add(powerService.findById(id));
				}
				// 更新权限
				role.setPowers(set);
			}
			// 更新角色
			roleService.update(role);
		}
		return "redirect:/role/listUI.do";
	}

	/**
	 * 权限添加界面
	 * 
	 * @author 程菊飞
	 * @return 2016/11/17
	 */
	@RequestMapping("/addUI")
	public String addUI(HttpServletRequest request) {
		// 添加权限列表
		request.setAttribute("powerList", powerService.findAll());
		return "WEB-INF/detail/saveRole";
	}

	/**
	 * 权限增加操作
	 * 
	 * @author 程菊飞
	 * @param role
	 * @return 2016/11/17
	 */
	@RequestMapping("/add")
	public String add(Role role, Integer[] powerIds) {
		if (role != null) {
			if (powerIds != null) {
				// 将选中的权限添加到角色中
				for (Integer id : powerIds) {
					role.getPowers().add(powerService.findById(id));
				}
			}
			roleService.save(role);
		}
		return "redirect:/role/listUI.do";
	}

	/**
	 * 权限删除操作
	 * 
	 * @author 程菊飞
	 * @param id
	 * @return 2016/11/17
	 */
	@RequestMapping("/delete")
	public String delete(Integer id) {
		try {
			if (id != null) {
				roleService.deleteById(id);
			}
		} catch (Exception e) {
			// 删除失败，事务自动回滚，跳转到list页面
			return "redirect:/role/listUI.do";
		}
		return "redirect:/role/listUI.do";
	}
}
