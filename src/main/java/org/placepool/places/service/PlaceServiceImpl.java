package org.placepool.places.service;

import java.util.Date;
import java.util.List;

import org.placepool.places.dao.PlaceDao;
import org.placepool.places.domain.PlaceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

	private static final Class<?> ENT_CLASS = PlaceEntity.class;

	@Autowired
	private PlaceDao placeDao;

	@Override
	public PlaceEntity saveOrUpdate(PlaceEntity place) {
		PlaceEntity result = place;
		if (place == null) {
			return null;
		}
		if (place.getId() == null) {
			place.setCreateDate(new Date());
			place.setPublishDate(null);
			placeDao.saveOrUpdate(place);
			result = place;
		} else {
			PlaceEntity oldPlace = (PlaceEntity) placeDao.get(ENT_CLASS, place.getId());
			oldPlace.setDescription(place.getDescription());
			oldPlace.setName(place.getName());
			oldPlace.setInfo(place.getInfo());
			placeDao.saveOrUpdate(oldPlace);
			result = oldPlace;
		}
		return result;
	}

	@Override
	public List<PlaceEntity> listNotPublished() {
		return placeDao.listNotPublished();
	}

	@Override
	public List<PlaceEntity> listPublished() {
		return placeDao.listPublished();
	}

	@Override
	public PlaceEntity getPublished(Long id) {
		return placeDao.getPublished(id);
	}

	@Override
	public PlaceEntity getNotPublished(Long id) {
		return placeDao.getNotPublished(id);
	}

}
