package com.miao.room.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.miao.core.BaseDaoImpl;
import com.miao.entity.Room;

/**
 * 直播间数据访问层实现
 * @author 宋禹龙
 * 2016/11/16
 */
public class RoomDaoImpl extends BaseDaoImpl<Room> implements RoomDao{

	@Override
	public List<Room> pageList(Integer currentPage, int i) {
		String hql = "from Room";
		Query query = currentSession().createQuery(hql);
		query.setFirstResult((currentPage-1)*i);
		query.setMaxResults(i);
		return query.getResultList();
	}

	@Override
	public List<Room> findBysearchName(String searchName) {
		String hql = "from Room where roomName like ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, "%"+searchName+"%");
		return query.getResultList();
	}
}
