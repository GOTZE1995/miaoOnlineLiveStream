package com.miao.power.service;

import java.util.List;

import com.miao.core.BaseService;
import com.miao.core.Page;
import com.miao.entity.Power;

/**
 * 权限业务逻辑层接口
 * @author sunlanyun
 * 2016/11/16
 */
public interface PowerService extends BaseService<Power>{

	//分页数据
	public List<Power> pageList(Integer currentPage, int i);
	
	//根据关键字搜索
	public List<Power> doSearch(String searchName);
	
	//封装页面
	public Page<Power> createPage(List<?> list, Integer currentPage, int i);
}







