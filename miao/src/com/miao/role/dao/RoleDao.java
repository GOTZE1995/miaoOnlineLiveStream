package com.miao.role.dao;

import com.miao.core.dao.BaseDao;
import com.miao.entity.Role;

/**
 * 角色数据访问层接口
 * 
 * @author 程菊飞 2016/11/17
 */
public interface RoleDao extends BaseDao<Role> {
	/**
	 * 根据角色名得到角色
	 * @author 程菊飞
	 * @param rolename
	 * @return
	 */
	public Role getRoleByRoleName(String rolename);
}
