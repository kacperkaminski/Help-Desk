package com.helpdesk.helpdeskbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.helpdeskbackend.model.Comment;
import com.helpdesk.helpdeskbackend.repository.CommentRepository;
import com.helpdesk.helpdeskbackend.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public List<Comment> getCommentsList(long id) {
		return commentRepository.getCommentsList(id);
	}

	@Override
	public void save(Comment comment) {
		commentRepository.save(comment);
		
	}

}
