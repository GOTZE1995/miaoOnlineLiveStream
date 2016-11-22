package com.miao.power.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.miao.core.dao.BaseDaoImpl;
import com.miao.entity.Power;

/**
 * 权限数据访问层实现
 * @author sunlanyun
 * 2016/11/16
 */
public class PowerDaoImpl extends BaseDaoImpl<Power> implements PowerDao{

	@Override
	public List<Power> pageList(Integer currentPage, int i) {
		String hql = "from Power";
		Query query = currentSession().createQuery(hql);
		query.setFirstResult((currentPage-1)*i);
		query.setMaxResults(i);
		return query.getResultList();
	}

	@Override
	public List<Power> findBysearchName(String searchName) {
		String hql = "from Power where powerName like ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, "%"+searchName+"%");
		return query.getResultList();
	}
}
