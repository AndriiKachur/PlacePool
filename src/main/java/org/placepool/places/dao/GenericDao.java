package org.placepool.places.dao;

import java.io.Serializable;
import org.placepool.places.domain.Identifiable;

public interface GenericDao<T> {

	void persist(Identifiable<T> entity);

	void delete(Identifiable<T> entity);

	void saveOrUpdate(Identifiable<T> entity);

	void update(Identifiable<T> entity);

	Identifiable<T> get(Class clazz, Serializable id);

}
