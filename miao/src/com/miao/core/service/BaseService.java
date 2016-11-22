package com.miao.core.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
	
	/**
	 * 保存一个对象
	 * @param entity 保存的对象
	 */
	public void save(T entity);
	
	/**
	 * 更新一个对象
	 * @param object 更新的对象
	 */
	public void update(T entity);
	
	/**
	 * 根据id删除
	 * @param id 主键id
	 */
	public void deleteById(Serializable id);
	
	/**
	 * 根据id查找
	 * @param id 主键id
	 */
	public T findById(Serializable id);
	
	/**
	 * 查询全部对象
	 */
	public List<T> findAll();
}
