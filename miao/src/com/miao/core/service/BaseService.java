package com.miao.core.service;

import java.io.Serializable;
import java.util.List;

import com.miao.core.utils.Page;
import com.miao.entity.Power;

public interface BaseService<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * 更新一个对象
	 * 
	 * @param object
	 */
	public void update(T entity);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void deleteById(Serializable id);

	/**
	 * 根据id查找
	 * 
	 * @param id
	 */
	public T findById(Serializable id);

	/**
	 * 查询全部对象
	 */
	public List<T> findAll();
	
	/**
	 * 分页查询
	 * @param currentPage
	 * @param i
	 * @return
	 */
	public List<T> pageList(Integer currentPage, int i);

	/**
	 * 封装页面
	 * @param list
	 * @param currentPage
	 * @param i
	 * @return
	 */
	public Page<T> createPage(List<?> list, Integer currentPage, int i);
}
