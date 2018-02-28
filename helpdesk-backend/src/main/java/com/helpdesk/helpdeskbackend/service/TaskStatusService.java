package com.helpdesk.helpdeskbackend.service;

import com.helpdesk.helpdeskbackend.model.TaskStatus;

public interface TaskStatusService {

	TaskStatus getOpenStatus();

	TaskStatus getStatus(int status);

}
