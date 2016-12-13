package com.miao.movie.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.movie.dao.MovieDao;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
	
	@Resource
	private MovieDao movieDao;
	@Override
	public void save(Object object) {
		movieDao.save(object);
	}

	@Override
	public void update(Object object) {
		movieDao.update(object);
	}

	@Override
	public void delete(Object object) {
		movieDao.delete(object);
	}

	@Override
	public Object ReadByID(String targetName,int id) {
		return movieDao.ReadSingle(targetName,"id", id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List ReadAll(String targetName) {
		return movieDao.ReadAll(targetName);
	}

	public MovieDao getBaseDao() {
		return movieDao;
	}

	public void setBaseDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Override
	public List ReadByProperty(String targetName, String propertyName,
			Object propertyValue) {
		return movieDao.ReadByProperty(targetName, propertyName, propertyValue);
	}

	@Override
	public Object ReadSingle(String targetName, String propertyName,
			Object propertyValue) {
		return movieDao.ReadSingle(targetName, propertyName, propertyValue);
	}

	@Override
	public List ReadLimitedByOrder(String targetName, String propertyName,
			int num, String order) {
		return movieDao.ReadLimitedByOrder(targetName,propertyName,num,order);
	}
	
	@Override
	public int ReadCount(String targetName) {
		return movieDao.ReadCount(targetName);
	}

	@Override
	public int ReadCountByProperty(String targetName,String propertyName, Object value) {
		return movieDao.ReadCountByProperty(targetName,propertyName,value);
	}

	@Override
	public List ReadByPropertyAndLimitedByOrder(String targetName,
			String readpropertyName, Object readvalue,
			String orderpropertyName, int num, String order) {
		return movieDao.ReadByPropertyAndLimitedByOrder(targetName, readpropertyName, readvalue,
				orderpropertyName, num, order);
	}
}
