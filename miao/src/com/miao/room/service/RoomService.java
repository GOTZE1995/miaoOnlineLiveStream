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
	// 分页
	public List<Room> pageList(Integer currentPage, int i);

	// 根据关键字搜索
	public List<Room> doSearch(String searchName);

	// 封装页面
	public Page<Room> createPage(List<?> list, Integer currentPage, int i);
}
