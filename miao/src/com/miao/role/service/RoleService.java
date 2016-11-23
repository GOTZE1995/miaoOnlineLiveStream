package com.miao.role.service;

import java.util.List;

import com.miao.core.service.BaseService;
import com.miao.core.utils.Page;
import com.miao.entity.Role;

/**
 * 角色业务逻辑接口
 * @author 程菊飞
 * 2016/11/17
 */
public interface RoleService extends BaseService<Role>{

	//分页数据
	public List<Role> pageList(Integer currentPage, int i);
	
	//根据关键字搜索
	public List<Role> doSearch(String searchName);
	
	//封装页面
	public Page<Role> createPage(List<?> list, Integer currentPage, int i);
}









