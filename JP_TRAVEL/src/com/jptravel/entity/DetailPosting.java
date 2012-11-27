package com.jptravel.entity;

public class DetailPosting extends Posting {
	private double latitude;
	private double longitude;
	private boolean isBookmarked;
	private Comment[] commenList;
	
	
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
	public boolean isBookmarked() {
		return isBookmarked;
	}
	public void setBookmarked(boolean isBookmarked) {
		this.isBookmarked = isBookmarked;
	}
	public Comment[] getCommenList() {
		return commenList;
	}
	public void setCommenList(Comment[] commenList) {
		this.commenList = commenList;
	}
	
}
