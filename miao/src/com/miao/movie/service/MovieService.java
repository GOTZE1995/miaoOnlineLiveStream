package com.miao.movie.service;

import java.util.List;

import com.miao.core.service.BaseService;
import com.miao.core.utils.Page;
import com.miao.entity.Room;
import com.miao.entity.Video;


/**
 * @author fengxin
 */
public interface MovieService extends BaseService<Video>{
	
	/**
	 *根据用户的搜索名进行搜索
	 * @author 程菊飞
	 * @param searchName
	 * @return
	 */
	public List<Video> searchMovies(String searchName);
	
	/**
	 * 对点播间列表分页显示
	 * @author 程菊飞
	 * @param list
	 * @param currentPage
	 * @param i
	 * @return
	 */
	public Page<Video> pageListMovie(List list,Integer currentPage, int i);
	
	 
	/**
	 * 根据点播间的添加的时间顺序进行显示
	 * @author 程菊飞
	 * @param currentPage
	 * @param i
	 * @return
	 */
	public List<Video> pageListByTime(Integer currentPage, int i);
}
