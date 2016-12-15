package com.miao.attendence.dao;

import java.util.List;

import com.miao.core.dao.BaseDao;
import com.miao.entity.User;

public interface AttendenceDao extends BaseDao<User> {

	/**
	 * 查询到对应的班级的所有对象
	 * @author 程菊飞
	 * @param username
	 * @return 
	 */
	public List<User> findAllStudentsByClassName(String className);
	/**
	 * 查询到所有的班级的名字
	 * @author 程菊飞
	 * @return
	 */
	public List findAllClassNames();
}
