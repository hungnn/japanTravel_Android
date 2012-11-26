package com.jptravel.entity;

public class Comment {
	private int commentId;
	private int userId;
	private String userImageProfileURL;
	private String userName;
	private String content;
	private String time;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserImageProfileURL() {
		return userImageProfileURL;
	}
	public void setUserImageProfileURL(String userImageProfileURL) {
		this.userImageProfileURL = userImageProfileURL;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
