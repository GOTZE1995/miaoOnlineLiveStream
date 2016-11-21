package com.miao.core;

import java.io.Serializable;
import java.util.List;
/**
 * 通用Dao
 * @author Jupiter
 * 
 * @param <T>
 */
public interface BaseDao<T> {
	
	/**
	 * 添加一个对象
	 * @param object 要添加的对象
	 */
	public void save(T object);
	
	/**
	 * 更新一个对象
	 * @param object 要更新的对象
	 */
	public void update(T object);
	
	/**
	 * 根据id删除
	 * @param id 主键
	 */
	public void deleteById(Serializable id);
	
	/**
	 * 查找全部
	 */
	public List<T> findAll();
	
	/**
	 * 根据主键查找
	 * @param id 主键
	 */
	public T findById(Serializable id);
}
