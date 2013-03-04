package org.placepool.places.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.placepool.places.dao.GenericDao;
import org.placepool.places.domain.PlaceEntity;
import org.placepool.places.domain.PlaceInfoEntity;
import org.placepool.places.domain.PlaceInfoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class HomeResource {

	@Autowired
	private GenericDao<Long> placeDao;

	@GET
	public Response restTest(@QueryParam("id") Long id) {

		if (id != null) {
			return Response.ok(placeDao.get(PlaceEntity.class, id)).build();
		}

		PlaceEntity ent = new PlaceEntity();
		ent.setName(new Object().toString());

		PlaceInfoEntity info = new PlaceInfoEntity();
		info.setType(PlaceInfoType.CONTACT);
		info.setValue(new Object().toString());
		info.setPlace(ent);

		ent.getInfo().add(info);

		placeDao.persist(ent);
		return Response.ok(ent).build();
	}
}
