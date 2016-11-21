package com.miao.user.dao;

import java.util.List;

import com.miao.core.BaseDao;
import com.miao.entity.User;
/**
 * 用户数据访问层
 * @author songyulong/sunlanyun/chengjufei/fengxin
 */
public interface UserDao extends BaseDao<User>{
	
	/**
	 * 根据帐号和密码查询
	 * @param name 帐号
	 * @param password 密码
	 * @return 查询到的对象
	 */
	public User findByAccountAndPass(String name,String password);
	
	/**
	 * 根据帐号查询是否重复
	 * @param username 用户名
	 * @return 查询到的对象
	 */
	public List<User> findAllUserByName(String userName);
	
	/**
	 * 根据邮箱查询是否重复
	 * @param email 邮箱
	 * @return 查询到的对象
	 */
	public List<User> findAllUsersByEmail(String email);
	
	/**分页*/
	public List<User> pageList(Integer currentPage, int i);
		
	/**模糊查询*/
	public List<User> findBysearchName(String searchName);
}






