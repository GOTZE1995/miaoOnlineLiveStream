package com.miao.attendence.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.query.Query;

import com.miao.core.dao.BaseDaoImpl;
import com.miao.entity.User;

public class AttendenceDaoImpl extends BaseDaoImpl<User> implements AttendenceDao {
	@Override
	public List<User> findAllStudentsByClassName(String className){
		String hql="from User u where u.role.roleName like '学生'And u.className like ?0";
		Query query=currentSession().createQuery(hql);
		query.setParameter(0, className);
		return query.getResultList();	
	}
	
	@Override
	public List findAllClassNames(){
		String sql="SELECT DISTINCT className from User";
		SQLQuery query=currentSession().createSQLQuery(sql);
		List classNames=query.list();
		return classNames;
	}
}
