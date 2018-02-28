package com.helpdesk.helpdeskbackend.service;

import java.util.List;

import com.helpdesk.helpdeskbackend.model.Comment;

public interface CommentService {

	List<Comment> getCommentsList(long id);

	void save(Comment comment);

}
