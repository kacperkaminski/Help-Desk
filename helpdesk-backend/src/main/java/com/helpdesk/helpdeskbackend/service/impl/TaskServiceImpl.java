package com.helpdesk.helpdeskbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.helpdeskbackend.model.Task;
import com.helpdesk.helpdeskbackend.repository.TaskRepository;
import com.helpdesk.helpdeskbackend.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<>();
		taskRepository.findAll().forEach(tasks::add);
		return tasks;
	}

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(long id) {
		return taskRepository.getTaskById(id);
	}

}
