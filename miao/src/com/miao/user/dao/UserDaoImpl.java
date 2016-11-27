package com.miao.user.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.miao.core.dao.BaseDaoImpl;
import com.miao.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@SuppressWarnings("deprecation")
	@Override
	public User findByAccountAndPass(String name, String password) {
		String hql = "from User where userName = ? and password = ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, name);
		query.setParameter(1, password);
		return (User) query.uniqueResult();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<User> findAllUserByName(String userName) {
		String hql = "from User where userName=?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, userName);
		return query.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<User> findAllUsersByEmail(String email) {
		String hql = "from User where email=?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, email);
		return query.getResultList();
	}



}
