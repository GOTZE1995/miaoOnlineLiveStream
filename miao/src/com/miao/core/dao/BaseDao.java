package com.miao.core.dao;

import java.io.Serializable;
import java.util.List;

import com.miao.entity.User;

/**
 * 通用Dao
 * 
 * @author Jupiter
 * 
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 添加一个对象
	 * 
	 * @param object
	 */
	public void save(T object);

	/**
	 * 更新一个对象
	 * 
	 * @param object
	 */
	public void update(T object);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void deleteById(Serializable id);

	/**
	 * 查找全部
	 */
	public List<T> findAll();

	/**
	 * 根据主键查找
	 * 
	 * @param id
	 */
	public T findById(Serializable id);
	
	/**
	 * 分页查询
	 * @param currentPage
	 * @param i
	 * @return
	 */
	public List<T> pageList(Integer currentPage, int i);

}
