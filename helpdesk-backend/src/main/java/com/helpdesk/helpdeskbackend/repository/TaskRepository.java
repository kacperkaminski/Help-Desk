package com.helpdesk.helpdeskbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpdesk.helpdeskbackend.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	@Query("FROM Task WHERE id=:taskId")
	Task getTaskById(@Param("taskId")long id);

}
