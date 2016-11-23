package com.miao.role.dao;

import java.util.List;

import com.miao.core.dao.BaseDao;
import com.miao.entity.Role;

/**
 * 角色数据访问层接口
 * @author 程菊飞
 * 2016/11/17
 */
public interface RoleDao extends BaseDao<Role>{
	//分页
	public List<Role> pageList(Integer currentPage, int i);
	
	//模糊查询
	public List<Role> findBysearchName(String searchName);
}






