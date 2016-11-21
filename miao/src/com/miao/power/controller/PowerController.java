package com.miao.power.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miao.core.Page;
import com.miao.entity.Power;
import com.miao.power.service.PowerService;

/**
 * 权限控制器
 * @author 孙兰云
 * 2016/11/16
 */
@Controller
@RequestMapping("power")
public class PowerController {
	
	@Resource
	private PowerService powerService;
	
	/**
	 * 权限列表界面+查询功能
	 * @author 孙兰云
	 * @param request
	 * @return jsp页面
	 * 2016/11/17
	 */
	@RequestMapping("/listUI")
	public String listUI(@RequestParam(required=false,defaultValue="1") Integer currentPage , HttpServletRequest request,
			String searchName){
		Page<Power> page ;
		//用户输入了搜索数据
		if (searchName !=null &&  !"".equals(searchName)) {
			List<Power> list = powerService.doSearch(searchName);
			page = powerService.createPage(list, currentPage, 8);
			request.setAttribute("searchName", searchName);
		}
		//用户没有输入搜索数据
		else{
			page = powerService.createPage(null, currentPage, 8);
		}
		request.setAttribute("page", page);
		request.setAttribute("powerList", powerService.findAll());
		return "WEB-INF/detail/listPower";
	}
	
	/**
	 * 权限更新界面
	 * @author 孙兰云
	 * @param id
	 * @param request
	 * @return jsp页面
	 * 2016/11/17
	 */
	@RequestMapping("/updateUI")
	public String updateUI(Integer id ,HttpServletRequest request){
		if(id != null){
			request.setAttribute("powerInfo", powerService.findById(id));
		}
		return "WEB-INF/detail/updatePower";
	}
	
	/**
	 * 权限更新操作
	 * @author 孙兰云
	 * @param power
	 * @return
	 * 2016/11/17
	 */
	@RequestMapping("/update")
	public String update(Power power){
		if(power != null && power.getPowerId() != null){
			powerService.update(power);
		}
		return "redirect:/power/listUI.do";
	}
	
	/**
	 * 权限添加界面
	 * @author 孙兰云
	 * @return
	 * 2016/11/17
	 */
	@RequestMapping("/addUI")
	public String addUI() {
		return "WEB-INF/detail/savePower";
	}
	
	/**
	 * 权限增加操作
	 * @author 孙兰云
	 * @param power
	 * @return
	 * 2016/11/17
	 */
	@RequestMapping("/add")
	public String add(Power power){
		if(power != null){
			powerService.save(power);
			System.out.println(power.getPowerName());
		}
		return "redirect:/power/listUI.do";
	}
	
	/**
	 * 权限删除操作
	 * @author 孙兰云
	 * @param id
	 * @return
	 * 2016/11/17
	 */
	@RequestMapping("/delete")
	public String delete(Integer id){
		try{
			if(id != null){
				powerService.deleteById(id);
			}
		}catch(Exception e){
			//删除失败，事务自动回滚，跳转到list页面
			return "redirect:/power/listUI.do";
		}
		return "redirect:/power/listUI.do";
	}
}





