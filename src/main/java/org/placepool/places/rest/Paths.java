package org.placepool.places.rest;

public interface Paths {

	String ROOT = "/";

	interface Place {
		String ROOT = Paths.ROOT + "places/";

		String NOT_PUBLISHED = "new/";
	}

}
