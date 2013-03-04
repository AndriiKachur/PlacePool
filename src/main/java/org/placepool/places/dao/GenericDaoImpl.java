package org.placepool.places.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.placepool.places.domain.Identifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persist(Identifiable<T> entity) {
		sessionFactory.getCurrentSession().persist(entity);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void delete(Identifiable<T> entity) {
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void saveOrUpdate(Identifiable<T> entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void update(Identifiable<T> entity) {
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public Identifiable<T> get(Class clazz, Serializable id) {
		return (Identifiable<T>) sessionFactory.getCurrentSession().get(clazz, id);
	}

}