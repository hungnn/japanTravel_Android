package com.jptravel.entity;

public class User {
	private String userId;
	private String username;
	private String imageProfileURL;
	private String facebookId;
	private String accessToken;
	private String updateTime;
	private String fbAccesstoken;
	private String createTime;
	private boolean deleted;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImageProfileURL() {
		return imageProfileURL;
	}
	public void setImageProfileURL(String imageProfileURL) {
		this.imageProfileURL = imageProfileURL;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getFbAccesstoken() {
		return fbAccesstoken;
	}
	public void setFbAccesstoken(String fbAccesstoken) {
		this.fbAccesstoken = fbAccesstoken;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
