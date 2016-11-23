package com.miao.room.dao;

import java.util.List;

import com.miao.core.dao.BaseDao;
import com.miao.entity.Room;

/**
 * 直播间数据访问层接口
 * @author 宋禹龙
 * 2016/11/16
 */
public interface RoomDao extends BaseDao<Room>{
	/**分页*/
	public List<Room> pageList(Integer currentPage, int i);
		
	/**模糊查询*/
	public List<Room> findBysearchName(String searchName);
}
