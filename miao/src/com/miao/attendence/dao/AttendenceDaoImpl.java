package com.miao.attendence.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.miao.core.dao.BaseDaoImpl;
import com.miao.entity.User;

public class AttendenceDaoImpl extends BaseDaoImpl<User> implements AttendenceDao {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<User> findAllStudents() {
		String hql="from User u where u.role.roleName like '学生'";
		Query query=currentSession().createQuery(hql);
		return query.getResultList();	
	}
}
