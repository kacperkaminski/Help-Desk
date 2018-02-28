package com.helpdesk.helpdeskbackend.service.pojo;

public class NewComment {

	private long authorId;
	private long taskId;
	private String message;

	public NewComment() {
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public NewComment(long authorId, long taskId, String message) {
		this.authorId = authorId;
		this.taskId = taskId;
		this.message = message;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
