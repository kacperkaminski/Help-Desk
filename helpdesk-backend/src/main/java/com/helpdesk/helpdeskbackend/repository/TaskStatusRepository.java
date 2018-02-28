package com.helpdesk.helpdeskbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpdesk.helpdeskbackend.model.TaskStatus;

@Repository
public interface TaskStatusRepository  extends JpaRepository<TaskStatus, Long>{

	@Query("FROM TaskStatus WHERE id=:statusId")
	public TaskStatus getStatusById(@Param("statusId") long statusId);
}
