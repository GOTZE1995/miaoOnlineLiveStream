package com.miao.user.service;

import java.util.List;

import com.miao.core.service.BaseService;
import com.miao.core.utils.Page;
import com.miao.entity.User;

/**
 * 用户业务逻辑层接口
 * 
 * @author songyulong/sunlanyun/chengjufei/fengxin
 *
 */
public interface UserService extends BaseService<User> {

	/**
	 * 用户登录
	 * 
	 * @param name
	 * @param password
	 * @return 用户对象
	 * @author sunlanyun 2016/11/15
	 */
	public User login(String name, String password);

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @author chengjufei 2016/11/15
	 */
	public void regist(User user);

	/**
	 * 用户名查询
	 * 
	 * @param userName
	 * @return 查到的重复用户
	 * @author chengjufei 2016/11/15
	 */
	public List<User> findAllUsersByName(String userName);

	/**
	 * email查询
	 * 
	 * @param email
	 * @return 查到的重复用户
	 * @author chengjufei 2016/11/15
	 */
	public List<User> findAllUsersByEmail(String email);


	/** 根据关键字搜索 */
	public List<User> doSearch(String searchName);


}
