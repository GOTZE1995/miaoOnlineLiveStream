package com.miao.role.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.core.service.BaseServiceImpl;
import com.miao.core.utils.Page;
import com.miao.entity.Role;
import com.miao.role.dao.RoleDao;

/**
 * 角色业务逻辑层实现
 * 
 * @author 程菊飞 2016/11/17
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	private RoleDao roleDao;

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> doSearch(String searchName) {
		return roleDao.findBysearchName(searchName);
	}

}
