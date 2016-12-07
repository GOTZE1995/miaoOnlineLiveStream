package com.miao.room.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.miao.core.dao.BaseDaoImpl;
import com.miao.entity.Room;

/**
 * 直播间数据访问层实现
 * 
 * @author 宋禹龙 2016/11/16
 */
public class RoomDaoImpl extends BaseDaoImpl<Room> implements RoomDao {

	@Override
	public List<Room> pageListByStatus(Integer currentPage, int i) {
		String hql = "FROM Room ORDER BY status desc ";
		Query<Room> query = this.currentSession().createQuery(hql);
		query.setFirstResult((currentPage-1)*i);
		query.setMaxResults(i);
		return query.getResultList();
	}


}
