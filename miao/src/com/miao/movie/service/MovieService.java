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
	/**
	  * 根据ID读取一个指定名称的对象
	  * @param targetName 对象的名称
	  * @param id 对象的ID
	  * @return 一个对象
	  */
	 public Object ReadByID(String targetName,int id);
	 
	 @SuppressWarnings("rawtypes")
	 
	 /**
	  * 获取视频列表
	  * @param targetName 对象类型名称
	  * @param propertyName 对象中属性的名称
	  * @param propertyValue 对象中属性的值
	  * @return 视频列表
	  */
	 public List ReadByProperty(String targetName,String propertyName,int propertyValue);

	 /**
	  * 根据“属性-值”获取一个指定类型的对象
	  * @param targetName 对象类型名称
	  * @param propertyName 对象中属性的名称
	  * @param propertyValue 对象中属性的值
	  * @return 一个对象
	  */
	 public Object ReadSingle(String targetName,String propertyName,Object propertyValue);
}
