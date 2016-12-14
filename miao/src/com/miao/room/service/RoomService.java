package com.miao.room.service;

import java.util.List;

import com.miao.core.service.BaseService;
import com.miao.core.utils.Page;
import com.miao.entity.Room;

/**
 * 直播间业务逻辑层接口
 * 
 * @author 宋禹龙 2016/11/16
 */
public interface RoomService extends BaseService<Room> {

	/**
	 *  根据直播间状态排序 分页
	 * @param currentPage
	 * @param i
	 * @return
	 */
	public List<Room> pageListByStatus(Integer currentPage, int i);

	public Page<Room> pageListRoom(List list,Integer currentPage, int i);
	
	

}
