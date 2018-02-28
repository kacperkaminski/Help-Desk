package com.helpdesk.helpdeskbackend.service.pojo;

import java.util.List;

import com.helpdesk.helpdeskbackend.model.Comment;
import com.helpdesk.helpdeskbackend.model.Task;

public class TaskWithComments {
	private Task task;
	private List<Comment> comments;

	public TaskWithComments() {

	}

	public TaskWithComments(Task task, List<Comment> comments) {
		this.task = task;
		this.comments = comments;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
