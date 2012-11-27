package com.jptravel.entity;


public class Event {
	
	private String id;
	private String placeName;
	private String name;
	private String description;
	private String startTime;
	private String endTime;
	private String eventImagimageURLeUrl;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEventImagimageURLeUrl() {
		return eventImagimageURLeUrl;
	}
	public void setEventImagimageURLeUrl(String eventImagimageURLeUrl) {
		this.eventImagimageURLeUrl = eventImagimageURLeUrl;
	}
	
}
