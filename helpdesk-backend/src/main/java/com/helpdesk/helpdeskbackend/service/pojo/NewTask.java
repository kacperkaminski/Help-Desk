package com.helpdesk.helpdeskbackend.service.pojo;

public class NewTask {

	private long ownerId;
	private String title;
	private String message;

	public NewTask() {

	}

	public NewTask(long ownerId, String title, String message) {
		this.ownerId = ownerId;
		this.title = title;
		this.message = message;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
