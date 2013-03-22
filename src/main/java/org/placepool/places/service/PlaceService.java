package org.placepool.places.service;

import java.util.List;

import org.placepool.places.domain.PlaceEntity;

public interface PlaceService {
	
	PlaceEntity saveOrUpdate(PlaceEntity place);
	
	List<PlaceEntity> listNotPublished();
	
	List<PlaceEntity> listPublished();
	
	PlaceEntity getPublished(Long id);
	
	PlaceEntity getNotPublished(Long id);

}
