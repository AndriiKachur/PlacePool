package org.placepool.places.dao;

import java.util.List;

import org.placepool.places.domain.PlaceEntity;

public interface PlaceDao extends GenericDao<Long> {
	
	PlaceEntity getPublished(Long id);
	
	PlaceEntity getNotPublished(Long id);
	
	List<PlaceEntity> listPublished();
	
	List<PlaceEntity> listNotPublished();

}
