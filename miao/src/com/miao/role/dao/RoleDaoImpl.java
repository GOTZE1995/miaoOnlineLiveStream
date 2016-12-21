package com.miao.role.dao;

import org.hibernate.query.Query;

import com.miao.core.dao.BaseDaoImpl;
import com.miao.entity.Role;
import com.miao.entity.User;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	public Role getRoleByRoleName(String rolename){
		String hql = "from Role where roleName = ?0";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, rolename);
		Role role=(Role) query.uniqueResult();
		return role;
	}
}
