package com.miao.movie.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.miao.core.dao.BaseDaoImpl;
import com.miao.entity.Room;
import com.miao.entity.Video;


public class MovieDaoImpl extends BaseDaoImpl<Video> implements MovieDao{
	
	public List<Video> searchMovie(String searchName){
		//根据搜索名获取视频
		String hql = "FROM Video where name like ? ";
		Query<Video> query = this.currentSession().createQuery(hql);
		query.setParameter(0, "%"+searchName+"%");
		return query.getResultList();
	}
	
	@Override
	public List<Video> pageListByTime(Integer currentPage, int i) {
		//根据视频添加的事件顺序来得到视频
		String hql = "FROM Video ORDER BY edittime desc ";
		Query<Video> query = this.currentSession().createQuery(hql);
		query.setMaxResults(i);
		query.setFirstResult((currentPage-1)*i);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Video> ReadLimitedByOrder(final String targetName,
			final String propertyName, final int num, final String order) {

		return (List<Video>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException{
				String hql ="from "+targetName+" as "+targetName+ " order by "+targetName+"." + propertyName+ " " + order;
				Query query = session.createQuery(hql);
				query.setMaxResults(num);
				return query.getResultList();
			}
		});
	}

}
