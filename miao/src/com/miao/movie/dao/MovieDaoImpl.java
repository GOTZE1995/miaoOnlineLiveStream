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
	
	public List<Video> searchMovies(String searchName){
		String hql = "FROM Video where name like ? ";
		Query<Video> query = this.currentSession().createQuery(hql);
		query.setParameter(0, searchName);
		return query.getResultList();
	}
	
	@Override
	public List<Video> pageListByTime(Integer currentPage, int i) {
		String hql = "FROM Video ORDER BY edittime desc ";
		Query<Video> query = this.currentSession().createQuery(hql);
		query.setFirstResult((currentPage-1)*i);
		query.setMaxResults(i);
		return query.getResultList();
	}
	
	@Override
	public Object ReadSingle(final String targetName,final String propertyName, final Object value) {
		return (Object) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session)
					throws HibernateException{
				String hql = "from "+targetName+" as "+targetName+" where "+targetName+"." + propertyName + "=:value";
				Query query = session.createQuery(hql);
				query.setParameter("value", value);
				return query.uniqueResult();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Video> ReadByProperty(final String targetName, final String propertyName,
			final int value) {
		return (List<Video>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException{
				String hql = "from "+targetName+" as "+targetName+" where "+targetName+"." + propertyName + "=:value";
				Query query = session.createQuery(hql);
				query.setParameter("value", value);
				return query.list();
			}
		});
	}
}
