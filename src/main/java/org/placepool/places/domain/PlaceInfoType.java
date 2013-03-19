package org.placepool.places.domain;

public enum PlaceInfoType {
	CONTACT("CONTACT"), IMAGE_URL("IMAGE_URL"), DESC_URL("DESC_URL");
	
	private final String text;
	
	private PlaceInfoType(String desc) {
		this.text = desc;
	}
}
