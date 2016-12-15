package com.miao.movie.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.core.service.BaseServiceImpl;
import com.miao.core.utils.Page;
import com.miao.entity.Room;
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
	public List<Video> searchMovies(String searchName){
		return movieDao.searchMovies(searchName);
	}
	
	@Override
	public Page<Video> pageListMovie(List list,Integer currentPage, int i){
		Page<Video> page = new Page<Video>(currentPage, i);
		if (list != null) {
			//列表有数据
			page.setTotalCount(list.size());
			page.setList(list);
			return page;
		}else{
		list = findAll();
		page.setTotalCount(list.size());
		page.setList(pageListByTime(currentPage,i));
		return page;
		}
	}
	
	@Override
	public List<Video> pageListByTime(Integer currentPage, int i){
		return movieDao.pageListByTime(currentPage, i);
	}
}
