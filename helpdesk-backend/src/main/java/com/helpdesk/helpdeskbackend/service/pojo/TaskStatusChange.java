package com.helpdesk.helpdeskbackend.service.pojo;

public class TaskStatusChange {

	private int status;
	private int userId;

	public TaskStatusChange() {
	}

	public TaskStatusChange(int status, int userId) {
		this.status = status;
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
