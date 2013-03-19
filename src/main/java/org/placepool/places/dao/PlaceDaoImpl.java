package org.placepool.places.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.placepool.places.domain.PlaceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PlaceDaoImpl extends GenericDaoImpl<Long> implements PlaceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PlaceEntity> listNotPublished() {
		return sessionFactory.getCurrentSession().createCriteria(PlaceEntity.class)
				.add(Restrictions.isNull("publishDate")).list();
	}

	@Override
	public List<PlaceEntity> listPublished() {
		return sessionFactory.getCurrentSession().createCriteria(PlaceEntity.class)
				.add(Restrictions.isNotNull("publishDate")).list();
	}

	@Override
	public PlaceEntity getPublished(Long id) {
		return (PlaceEntity) sessionFactory.getCurrentSession().createCriteria(PlaceEntity.class)
				.add(Restrictions.isNotNull("publishDate")).add(Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	public PlaceEntity getNotPublished(Long id) {
		return (PlaceEntity) sessionFactory.getCurrentSession().createCriteria(PlaceEntity.class)
				.add(Restrictions.isNull("publishDate")).add(Restrictions.eq("id", id)).uniqueResult();
	}
}
