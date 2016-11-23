package com.miao.room.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.core.service.BaseServiceImpl;
import com.miao.core.utils.Page;
import com.miao.entity.Room;
import com.miao.room.dao.RoomDao;

/**
 * 直播间业务逻辑层实现
 * 
 * @author songyulong
 */
@Service("roomService")
public class RoomServiceImpl extends BaseServiceImpl<Room> implements RoomService {

	private RoomDao roomDao;

	@Resource
	public void setRoomDao(RoomDao roomDao) {
		super.setBaseDao(roomDao);
		this.roomDao = roomDao;
	}


	@Override
	public List<Room> doSearch(String searchName) {
		return roomDao.findBysearchName(searchName);
	}

}
