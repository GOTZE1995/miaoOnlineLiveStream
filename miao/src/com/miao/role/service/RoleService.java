package com.miao.role.service;

import com.miao.core.service.BaseService;
import com.miao.entity.Role;

/**
 * 角色业务逻辑接口
 * 
 * @author 程菊飞 2016/11/17
 */
public interface RoleService extends BaseService<Role> {
	/**
	 * 根据角色名得到角色
	 * @author 程菊飞
	 * @param rolename
	 * @return
	 */
	public Role getRoleByRoleName(String rolename);
}
