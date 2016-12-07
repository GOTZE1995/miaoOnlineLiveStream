package com.miao.room.dao;

import java.util.List;

import com.miao.core.dao.BaseDao;
import com.miao.entity.Room;

/**
 * 直播间数据访问层接口
 * 
 * @author 宋禹龙 2016/11/16
 */
public interface RoomDao extends BaseDao<Room> {

	public List<Room> pageListByStatus(Integer currentPage, int i);

}
