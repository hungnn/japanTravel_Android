package com.jptravel.entity;

public class Posting {
	private int id;
	private String username;
	private String userImageProfile;
	private String postTime;
	private String thumbURL;
	private String URL;
	private String placeName;
	private String description;
	private int commentCount;
	private int bookmarkCount;
	private int isBookmarked;
	private int bookmarkId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserImageProfile() {
		return userImageProfile;
	}
	public void setUserImageProfile(String userImageProfile) {
		this.userImageProfile = userImageProfile;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getThumbURL() {
		return thumbURL;
	}
	public void setThumbURL(String thumbURL) {
		this.thumbURL = thumbURL;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getBookmarkCount() {
		return bookmarkCount;
	}
	public void setBookmarkCount(int bookmarkCount) {
		this.bookmarkCount = bookmarkCount;
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
}
