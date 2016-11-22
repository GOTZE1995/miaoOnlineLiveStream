package com.miao.user.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.miao.core.dao.BaseDaoImpl;
import com.miao.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public User findByAccountAndPass(String name, String password) {
		String hql="from User where userName = ? and password = ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, name);
		query.setParameter(1, password);
		return (User)query.uniqueResult();
	}

	@Override
	public List<User> findAllUserByName(String userName) {
		String hql = "from User where userName=?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, userName);
		return query.getResultList();
	}

	@Override
	public List<User> findAllUsersByEmail(String email) {
		String hql="from User where email=?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, email);
		return query.getResultList();
	}

	@Override
	public List<User> pageList(Integer currentPage, int i) {
		String hql = "from User";
		Query query = currentSession().createQuery(hql);
		query.setFirstResult((currentPage-1)*i);
		query.setMaxResults(i);
		return query.getResultList();
	}

	@Override
	public List<User> findBysearchName(String searchName) {
		String hql = "from User where userName like ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, "%"+searchName+"%");
		return query.getResultList();
	}
}






