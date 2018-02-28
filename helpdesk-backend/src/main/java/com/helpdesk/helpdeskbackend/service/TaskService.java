package com.helpdesk.helpdeskbackend.service;

import java.util.List;

import com.helpdesk.helpdeskbackend.model.Task;

public interface TaskService {

	List<Task> getAllTasks();

	Task save(Task task);

	Task getTaskById(long id);

}
