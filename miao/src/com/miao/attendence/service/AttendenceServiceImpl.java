package com.miao.attendence.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.attendence.dao.AttendenceDao;
import com.miao.core.service.BaseServiceImpl;
import com.miao.core.utils.Page;
import com.miao.entity.User;
import com.miao.user.dao.UserDao;

@Service("attendenceService")
public class AttendenceServiceImpl extends BaseServiceImpl<User> implements AttendenceService {
	@Resource
	private AttendenceDao attendenceDao;
	
	@Override
	public List<User> findAllStudentsByClassName(String className){
		return attendenceDao.findAllStudentsByClassName(className);
	}
	
	@Override
	public List findAllClassNames(){
		return attendenceDao.findAllClassNames();
	}
}
