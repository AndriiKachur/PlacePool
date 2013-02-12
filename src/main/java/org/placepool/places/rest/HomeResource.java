package org.placepool.places.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HomeResource {

	@GET
	public Response restTest() {
		return Response.ok("hello").build();
	}
}
