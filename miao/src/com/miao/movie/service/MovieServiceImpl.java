package com.miao.movie.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.core.service.BaseServiceImpl;
import com.miao.core.utils.Page;
import com.miao.entity.Video;
import com.miao.movie.dao.MovieDao;

@Service("movieService")
public class MovieServiceImpl extends BaseServiceImpl<Video> implements MovieService {
	
	private MovieDao movieDao;

	@Resource
	public void setMovieDao(MovieDao movieDao){
		super.setBaseDao(movieDao);
		this.movieDao=movieDao;
	}
	@Override
	public List<Video> searchMovie(String searchName){
		return movieDao.searchMovie(searchName);
	}
	
	@Override
	public Page<Video> pageListMovie(List list,Integer currentPage, int i){
		Page<Video> page = new Page<Video>(currentPage, i);
		if (list != null) {
			//列表有数据，表示用户输入了搜索信息
			page.setList(list);
			page.setTotalCount(list.size());
			return page;
		}else{
		//列表中没有数据，直接查询所有的视频信息
		list = findAll();
		page.setList(pageListByTime(currentPage,i));
		page.setTotalCount(list.size());
		return page;
		}
	}
	
	@Override
	public List<Video> pageListByTime(Integer currentPage, int i){
		//根据添加的时间来获取视频
		return movieDao.pageListByTime(currentPage, i);
	}
	
	@Override
	public List ReadLimitedByOrder(String targetName, String propertyName,
			int num, String order) {
		return movieDao.ReadLimitedByOrder(targetName,propertyName,num,order);
	}
}
