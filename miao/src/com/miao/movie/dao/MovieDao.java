package com.miao.movie.dao;

import java.util.List;

import com.miao.core.dao.BaseDao;
import com.miao.entity.Video;



/**
 * @author fengxin
 */

public interface MovieDao extends BaseDao<Video>{
	/**
	 * 根据用户的输入的搜索名获取视频列表
	 * @author 程菊飞
	 * @param searchName
	 * @return
	 */
	public List<Video> searchMovies(String searchName);
	
	/**
	 * 根据添加时间的先后来获取视频
	 * @author 程菊飞
	 * @param currentPage
	 * @param i
	 * @return
	 */
	public List<Video> pageListByTime(Integer currentPage, int i);
	
}
