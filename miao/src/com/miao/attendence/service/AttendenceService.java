package com.miao.attendence.service;

import java.util.List;

import com.miao.core.service.BaseService;
import com.miao.entity.User;

public interface AttendenceService extends BaseService<User> {
	public List<User> findAllStudents();
}
