package org.placepool.places.rest;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Path(Paths.ROOT)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class HomeResource {

}
