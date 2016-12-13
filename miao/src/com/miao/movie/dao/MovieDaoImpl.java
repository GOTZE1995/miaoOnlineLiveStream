package com.miao.movie.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.miao.core.utils.TransferString;;

public class MovieDaoImpl extends HibernateDaoSupport implements MovieDao{
	
	@Override
	public List findBysearchName(String searchName) {
		String hql = "from Video where Name like ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, "%" + searchName + "%");
		return query.getResultList();
	}

	@Override
	public void save(Object object) {
		getHibernateTemplate().save(object);
	}

	@Override
	public void delete(Object object) {
		getHibernateTemplate().delete(object);
	}

	@Override
	public void update(Object object) {
		getHibernateTemplate().update(object);
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
	public List<Object> ReadAll(String targetName) {
		String hql="from "+targetName;
		Query query = currentSession().createQuery(hql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> ReadByProperty(final String targetName, final String propertyName,
			final Object value) {
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException{
				String hql = "from "+targetName+" as "+targetName+" where "+targetName+"." + propertyName + "=:value";
				Query query = session.createQuery(hql);
				query.setParameter("value", value);
				return query.list();
			}
		});
	}
	

	@Override
	public List<Object> ReadByPropertyList(String targetName,
			String propertyName, Object value) {
		return null;
	}
	@Override
	public Integer ReadCount(final String targetName) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session)
					throws HibernateException{
				String hql = "select count(*) from "+targetName;

				return ((Number)session.createQuery(hql).iterate().next()).intValue();
			}
		});
	}
	
	@Override
	public Integer ReadCountByProperty(final String targetName,final String propertyName, final Object value) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException{
				String hql = "select count(*) from "+targetName+" as "+targetName+" where "+targetName+"." + propertyName + "=:value";
				
				Query query = session.createQuery(hql);
				query.setParameter("value", value);
				
				return ((Number)query.iterate().next()).intValue();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> ReadLimitedByOrder(final String targetName,
			final String propertyName, final int num, final String order) {

		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				String hql ="from "+targetName+" as "+targetName+ " order by "+targetName+"." + propertyName+ " " + order;
				Query query = session.createQuery(hql);
				query.setMaxResults(num);

				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> ReadByPropertyAndLimitedByOrder(final String targetName, final String readpropertyName,
			final Object readvalue,final String orderpropertyName, final int num, final String order) {
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hql = "from "+targetName+" as "+targetName+" where "+targetName+"." + readpropertyName + "=:value"+
						" order by "+targetName+"." + orderpropertyName+ " " + order;
				Query query = session.createQuery(hql);
				query.setParameter("value", readvalue);
				query.setMaxResults(num);
				return query.list();
			}
		});
	}
}
