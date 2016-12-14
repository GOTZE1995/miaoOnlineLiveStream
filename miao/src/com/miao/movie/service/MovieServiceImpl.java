package com.miao.movie.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.core.service.BaseServiceImpl;
import com.miao.entity.Video;
import com.miao.movie.dao.MovieDao;
import com.miao.user.dao.UserDao;

@Service("movieService")
public class MovieServiceImpl extends BaseServiceImpl<Video> implements MovieService {
	
	
	private MovieDao movieDao;

	@Resource
	public void setMovieDao(MovieDao movieDao){
		super.setBaseDao(movieDao);
		this.movieDao=movieDao;
	}

	@Override
	public Object ReadByID(String targetName,int id) {
		return movieDao.ReadSingle(targetName,"id", id);
	}

	@Override
	public List ReadByProperty(String targetName, String propertyName,
			int propertyValue) {
		return movieDao.ReadByProperty(targetName, propertyName, propertyValue);
	}

	@Override
	public Object ReadSingle(String targetName, String propertyName,
			Object propertyValue) {
		return movieDao.ReadSingle(targetName, propertyName, propertyValue);
	}
}
