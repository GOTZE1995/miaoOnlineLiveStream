package com.miao.attendence.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.attendence.dao.AttendenceDao;
import com.miao.core.service.BaseServiceImpl;
import com.miao.entity.User;

@Service("attendenceService")
public class AttendenceServiceImpl extends BaseServiceImpl<User> implements AttendenceService {
	@Resource
	private AttendenceDao attendenceDao;
	
	@Override
	public List<User> findAllStudents() {
		return attendenceDao.findAllStudents();
	}


}
