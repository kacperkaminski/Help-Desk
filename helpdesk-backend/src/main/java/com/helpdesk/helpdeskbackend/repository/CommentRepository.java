package com.helpdesk.helpdeskbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpdesk.helpdeskbackend.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	@Query("FROM Comment WHERE task_id=:taskId")
	List<Comment> getCommentsList(@Param("taskId") long id);

}
