package com.helpdesk.helpdeskbackend.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.helpdeskbackend.model.Comment;
import com.helpdesk.helpdeskbackend.model.Task;
import com.helpdesk.helpdeskbackend.model.TaskStatus;
import com.helpdesk.helpdeskbackend.model.User;
import com.helpdesk.helpdeskbackend.service.CommentService;
import com.helpdesk.helpdeskbackend.service.TaskService;
import com.helpdesk.helpdeskbackend.service.TaskStatusService;
import com.helpdesk.helpdeskbackend.service.UserService;
import com.helpdesk.helpdeskbackend.service.pojo.NewComment;
import com.helpdesk.helpdeskbackend.service.pojo.NewTask;
import com.helpdesk.helpdeskbackend.service.pojo.TaskStatusChange;
import com.helpdesk.helpdeskbackend.service.pojo.TaskWithComments;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskStatusService taskStatusService;

	@Autowired
	private CommentService commentService;

	@GetMapping
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/{id}")
	public TaskWithComments getTaskById(@PathVariable("id") long id) {
		TaskWithComments taskWithComments = new TaskWithComments(taskService.getTaskById(id),
				commentService.getCommentsList(id));
		return taskWithComments;
	}

	@PostMapping("/add")
	public ResponseEntity<?> addTask(@RequestBody NewTask newTask) {

		User user = userService.getUserById(newTask.getOwnerId());
		TaskStatus taskStatus = taskStatusService.getOpenStatus();
		String date = getDate();

		Task task = new Task();
		task.setOwner(user);
		task.setTitle(newTask.getTitle());
		task.setDate(date);
		task.setStatus(taskStatus);
		task = taskService.save(task);

		Comment comment = new Comment();
		comment.setTaskId(task);
		comment.setAuthor(user);
		comment.setMessage(newTask.getMessage());
		comment.setDate(date);
		commentService.save(comment);

		return new ResponseEntity<>(task, HttpStatus.CREATED);
	}

	@PostMapping("/comment/add")
	public ResponseEntity<?> addComment(@RequestBody NewComment newComment) {

		Task task = taskService.getTaskById(newComment.getTaskId());
		User user = userService.getUserById(newComment.getAuthorId());
		String date = getDate();

		// check if passed correct task id
		if (task == null)
			return new ResponseEntity<>("Could not find task", HttpStatus.NOT_ACCEPTABLE);
		// check if passed correct user id
		if (user == null)
			return new ResponseEntity<>("Could not find user", HttpStatus.NOT_ACCEPTABLE);

		Comment comment = new Comment();
		comment.setAuthor(user);
		comment.setMessage(newComment.getMessage());
		comment.setTaskId(task);
		comment.setDate(date);
		commentService.save(comment);

		TaskWithComments taskWithComments = getTaskById(newComment.getTaskId());
		return new ResponseEntity<>(taskWithComments, HttpStatus.CREATED);
	}

	@PostMapping("/task/{id}/status-change")
	public ResponseEntity<?> changeTastStatus(@PathVariable("id") long id, @RequestBody TaskStatusChange taskStatusChange) {
		
		Task task = taskService.getTaskById(id);
		TaskStatus taskStatus = taskStatusService.getStatus(taskStatusChange.getStatus());
		User user = userService.getUserById(taskStatusChange.getUserId());
		
		// check if passed correct task id
		if (task == null)
			return new ResponseEntity<>("Could not find task", HttpStatus.NOT_ACCEPTABLE);
		
		// check if passed correct task status id
		if (taskStatus == null)
			return new ResponseEntity<>("Could not find task", HttpStatus.NOT_ACCEPTABLE);
		
		// check if passed correct user id
		if (user == null)
			return new ResponseEntity<>("Could not find user", HttpStatus.NOT_ACCEPTABLE);
		
		// check if suer is Admin or HelpDesk Specialist or owner or is assigned to task
		if(user.getRole().getName() != "ROLE_ADMIN" || user.getRole().getName() != "ROLE_HD_SPECIALIST") 
			if(user.getId() != task.getOwner().getId() || user.getId() != task.getAssignedTo().getId())
				return new ResponseEntity<>("Insufficient perrmissions", HttpStatus.UNAUTHORIZED);
		
		task.setStatus(taskStatus);
		taskService.save(task);
		return new ResponseEntity<>(task, HttpStatus.CREATED);
	}

	private String getDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
}
