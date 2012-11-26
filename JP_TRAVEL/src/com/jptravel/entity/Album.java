package com.jptravel.entity;

public class Album {
	private int albumId;
	private String albumPhotoURL;
	private String name;
	private int year;
	
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public String getAlbumPhotoURL() {
		return albumPhotoURL;
	}
	public void setAlbumPhotoURL(String albumPhotoURL) {
		this.albumPhotoURL = albumPhotoURL;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}
