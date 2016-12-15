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
	public List<Video> searchMovie(String searchName);
	
	/**
	 * 根据添加时间的先后来获取视频
	 * @author 程菊飞
	 * @param currentPage
	 * @param i
	 * @return
	 */
	public List<Video> pageListByTime(Integer currentPage, int i);
	
	/**
	 * 获取多个指定类型的对象，可以限定获取对象数目的多少，并且根据特定的属性进行排序。
	 * @param targetName 对象类型名称
	 * @param propertyName 对象中属性的名称，用于排序
	 * @param num 结果对象列表的最大数目
	 * @param order 排序方式，可以选择“asc”或者“desc”
	 * @return 对象的列表
	 */
	public List<Video> ReadLimitedByOrder(String targetName, String propertyName,int num, String order);
	
}
