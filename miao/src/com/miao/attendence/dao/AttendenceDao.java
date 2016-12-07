package com.miao.attendence.dao;

import java.util.List;

import com.miao.core.dao.BaseDao;
import com.miao.entity.User;

public interface AttendenceDao extends BaseDao<User> {

	/**
	 * @author 程菊飞
	 * @param username
	 * @return 查询到的对象
	 */
	public List<User> findAllStudents();

}
