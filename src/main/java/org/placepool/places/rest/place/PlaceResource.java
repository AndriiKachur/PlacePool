package org.placepool.places.rest.place;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.placepool.places.domain.PlaceEntity;
import org.placepool.places.rest.Paths;
import org.placepool.places.service.PlaceService;
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
	private PlaceService placeService;

	@GET
	@Path("{id}")
	public Response getPublishedPlace(@PathParam("id") Long id) {
		return Response.ok(placeService.getPublished(id)).build();
	}

	@GET
	@Path(Paths.Place.NOT_PUBLISHED + "{id}")
	public Response getNotPublishedPlace(@PathParam("id") Long id) {
		return Response.ok(placeService.getNotPublished(id)).build();
	}

	@GET
	public Response getPublishedPlaces() {
		return Response.ok(placeService.listPublished()).build();
	}

	@GET
	@Path(Paths.Place.NOT_PUBLISHED)
	public Response getNotPublishedPlaces() {
		return Response.ok(placeService.listNotPublished()).build();
	}
	
	@PUT
	public Response saveOrUpdate(PlaceEntity place) {
		return Response.ok(placeService.saveOrUpdate(place)).build();
	}
}
