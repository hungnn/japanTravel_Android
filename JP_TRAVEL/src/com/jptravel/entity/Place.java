package com.jptravel.entity;

public class Place {
	private int placeId;
	private String placeImageURL;
	private String placeName;
	
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getPlaceImageURL() {
		return placeImageURL;
	}
	public void setPlaceImageURL(String placeImageURL) {
		this.placeImageURL = placeImageURL;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
}
