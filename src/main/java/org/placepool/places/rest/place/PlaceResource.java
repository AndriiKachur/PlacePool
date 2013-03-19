package org.placepool.places.rest.place;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.placepool.places.dao.PlaceDao;
import org.placepool.places.domain.PlaceEntity;
import org.placepool.places.rest.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Path(Paths.Place.ROOT)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class PlaceResource {

	@Autowired
	private PlaceDao placeDao;

	@GET
	@Path("{id}")
	public Response getPublishedPlace(@PathParam("id") Long id) {
		return Response.ok(placeDao.getPublished(id)).build();
	}

	@GET
	@Path(Paths.Place.NOT_PUBLISHED + "{id}")
	public Response getNotPublishedPlace(@PathParam("id") Long id) {
		return Response.ok(placeDao.getNotPublished(id)).build();
	}

	@GET
	public Response getPublishedPlaces() {
		return Response.ok(placeDao.listPublished()).build();
	}

	@GET
	@Path(Paths.Place.NOT_PUBLISHED)
	public Response getNotPublishedPlaces() {
		return Response.ok(placeDao.listNotPublished()).build();
	}
	
	@PUT
	public Response saveOrUpdate(PlaceEntity place) {
		if (place.getId() == null) {
			place.setCreateDate(new Date());
			place.setPublishDate(place.getCreateDate()); //TODO: remove after implementing moderator
			placeDao.saveOrUpdate(place);
			return Response.ok(place).build();
		} else {
			PlaceEntity oldPlace = (PlaceEntity) placeDao.get(place.getClass(), place.getId());
			if (place != null) {
				oldPlace.setDescription(place.getDescription());
				oldPlace.setName(place.getName());
				oldPlace.setInfo(place.getInfo());
				placeDao.saveOrUpdate(oldPlace);
				return Response.ok(oldPlace).build();
			}
		}
		return Response.ok().build();
	}
}
