package com.miao.role.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.miao.core.BaseDaoImpl;
import com.miao.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public List<Role> pageList(Integer currentPage, int i) {
		String hql = "from Role";
		Query query = currentSession().createQuery(hql);
		query.setFirstResult((currentPage-1)*i);
		query.setMaxResults(i);
		return query.getResultList();
	}

	@Override
	public List<Role> findBysearchName(String searchName) {
		String hql = "from Role where roleName like ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, "%"+searchName+"%");
		return query.getResultList();
	}

}
