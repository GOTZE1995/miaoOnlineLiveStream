package com.miao.movie.service;

import java.util.List;

import com.miao.core.service.BaseService;
import com.miao.core.utils.Page;
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
	  * 获取多个指定类型的对象，可以限定获取对象数目的多少，并且根据特定的属性进行排序。
	  * @param targetName 对象类型名称
	  * @param propertyName 对象中属性的名称，用于排序
	  * @param num 结果对象列表的最大数目
	  * @param order 排序方式，可以选择“asc”或者“desc”
	  * @return 对象的列表
	  */
	 public List ReadLimitedByOrder(String targetName,String propertyName,int num,String order);

}
