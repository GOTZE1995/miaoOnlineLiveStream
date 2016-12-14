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
	
	 /**
	  * 获取一个指定类型的对象
	  * @param targetName 对象类型名称
	  * @param propertyName 对象中属性的名称
	  * @param propertyValue 对象中属性的值
	  * @return 一个对象
	  */
	public Object ReadSingle(String targetName,String propertyName,Object propertyValue);
	
	 /**
	  * 获取视频列表
	  * @param targetName 对象类型名称
	  * @param propertyName 对象中属性的名称
	  * @param propertyValue 对象中属性的值
	  * @return 视频列表
	  */
	public List<Video> ReadByProperty(String targetName,String propertyName,int propertyValue);
}
