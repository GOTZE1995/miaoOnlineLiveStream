package com.miao.core.service;

import java.io.Serializable;
import java.util.List;

import com.miao.core.dao.BaseDao;
import com.miao.core.utils.Page;

public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		baseDao.deleteById(id);
	}

	@Override
	public T findById(Serializable id) {
		return baseDao.findById(id);
	}

	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<T> createPage(List list, Integer currentPage, int i) {
		Page<T> page = new Page<T>(currentPage, i);
		if (list != null) {
			page.setTotalCount(list.size());
			page.setList(list);
			return page;
		}
		list = findAll();
		page.setTotalCount(list.size());
		page.setList(pageList(currentPage, i));
		return page;
	}

	@Override
	public List<T> pageList(Integer currentPage, int i) {
		return baseDao.pageList(currentPage, i);
	}
	
	@Override
	public List<T> doSearch(String searchName) {
		return baseDao.findBysearchName(searchName);
	}

}
