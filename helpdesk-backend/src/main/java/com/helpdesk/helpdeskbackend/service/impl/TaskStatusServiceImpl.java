package com.helpdesk.helpdeskbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.helpdeskbackend.model.TaskStatus;
import com.helpdesk.helpdeskbackend.repository.TaskStatusRepository;
import com.helpdesk.helpdeskbackend.service.TaskStatusService;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

	@Autowired
	private  TaskStatusRepository taskStatusRepository;
	
	@Override
	public TaskStatus getOpenStatus() {
		return taskStatusRepository.getStatusById(1);
	}

	@Override
	public TaskStatus getStatus(int status) {
		return taskStatusRepository.getStatusById(status);
	}

}
