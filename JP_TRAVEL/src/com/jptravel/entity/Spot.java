package com.jptravel.entity;

public class Spot {
	private int spotId;
	private String name;
	private String address;
	private double latitude;
	private double longitude;
	private String photoURL;
	private String distance;
	private int imageNumber;
	private int isBookmarked;
	private int bookmarkId;
	private Posting[] postingList;
	private int placeId;
	private String placeName;
	
	public int getSpotId() {
		return spotId;
	}
	public void setSpotId(int spotId) {
		this.spotId = spotId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public int getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}
	public int getIsBookmarked() {
		return isBookmarked;
	}
	public void setIsBookmarked(int isBookmarked) {
		this.isBookmarked = isBookmarked;
	}
	public int getBookmarkId() {
		return bookmarkId;
	}
	public void setBookmarkId(int bookmarkId) {
		this.bookmarkId = bookmarkId;
	}
	public Posting[] getPostingList() {
		return postingList;
	}
	public void setPostingList(Posting[] postingList) {
		this.postingList = postingList;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
}
