package com.miao.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.core.service.BaseServiceImpl;
import com.miao.core.utils.Page;
import com.miao.entity.User;
import com.miao.user.dao.UserDao;

/**
 * 用户逻辑层实现
 * @author sunlanyun/chengjufei
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	private UserDao userDao;
	
	@Resource
	public void setUserDao(UserDao userDao){
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}
	
	@Override
	public User login(String name, String password) {
		return userDao.findByAccountAndPass(name, password);
	}

	@Override
	public void regist(User user) {
		userDao.save(user);
	}

	@Override
	public List<User> findAllUsersByName(String userName) {
		return userDao.findAllUserByName(userName);
	}

	@Override
	public List<User> findAllUsersByEmail(String email) {
		return userDao.findAllUsersByEmail(email);
	}

	@Override
	public List<User> pageList(Integer currentPage, int i) {
		return userDao.pageList(currentPage, i);
	}

	@Override
	public List<User> doSearch(String searchName) {
		return userDao.findBysearchName(searchName);
	}

	@Override
	public Page<User> createPage(List list, Integer currentPage, int i) {
		Page<User> page = new Page<User>(currentPage,i);
		if(list != null){
			page.setTotalCount(list.size());
			page.setList(list);
			return page;
		}
		list = findAll();
		page.setTotalCount(list.size());
		page.setList(pageList(currentPage,i));
		return page;
	}
}






