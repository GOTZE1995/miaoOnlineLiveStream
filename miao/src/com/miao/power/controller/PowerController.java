package com.miao.power.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.miao.entity.Power;
import com.miao.power.service.PowerService;
import com.miao.utils.Page;

/**
 * 权限控制器
 * 
 * @author 孙兰云 2016/11/16
 */
@Controller
@RequestMapping("power")
public class PowerController {

	@Resource
	private PowerService powerService;

	/**
	 * 权限列表界面+查询功能
	 * 
	 * @author 孙兰云
	 * @param request
	 * @return jsp页面 2016/11/17
	 */
	@RequestMapping("/listUI")
	public String listUI(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			HttpServletRequest request, String searchName) {
		Page<Power> page;
		List<Power> list = null;
		if (searchName != null && !"".equals(searchName)) {
			// 根据搜索名查询
			list = powerService.doSearch(searchName);
			// 将serchName设置成当前搜索的名称
			request.setAttribute("searchName", searchName);
		}
		// 将list放到分页对象中，若没有数据，默认为第一页
		page = powerService.createPage(list, currentPage, 8);

		request.setAttribute("page", page);
		request.setAttribute("powerList", powerService.findAll());

		return "WEB-INF/detail/listPower";
	}

	/**
	 * 权限更新界面
	 * 
	 * @author 孙兰云
	 * @param id
	 * @param request
	 * @return jsp页面 2016/11/17
	 */
	@RequestMapping("/updateUI")
	public String updateUI(Integer id, HttpServletRequest request) {
		if (id != null) {
			request.setAttribute("powerInfo", powerService.findById(id));
		}
		return "WEB-INF/detail/updatePower";
	}

	/**
	 * 权限更新操作
	 * 
	 * @author 孙兰云
	 * @param power
	 * @return 2016/11/17
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Power power) {
		if (power != null && power.getPowerId() != null) {
			powerService.update(power);
		}
		return "redirect:/power/listUI.do";
	}

	/**
	 * 权限添加界面
	 * 
	 * @author 孙兰云
	 * @return 2016/11/17
	 */
	@RequestMapping("/addUI")
	public String addUI() {
		return "WEB-INF/detail/savePower";
	}

	/**
	 * 权限增加操作
	 * 
	 * @author 孙兰云
	 * @param power
	 * @return 2016/11/17
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Power power) {
		if (power != null) {
			powerService.save(power);
		}
		return "redirect:/power/listUI.do";
	}

	/**
	 * 权限删除操作
	 * 
	 * @author 孙兰云
	 * @param id
	 * @return 2016/11/17
	 */
	@RequestMapping("/delete")
	public String delete(Integer id) {
		try {
			if (id != null) {
				powerService.deleteById(id);
			}
		} catch (Exception e) {
			// 删除失败，事务自动回滚，跳转到list页面
			return "redirect:/power/listUI.do";
		}
		return "redirect:/power/listUI.do";
	}
}
