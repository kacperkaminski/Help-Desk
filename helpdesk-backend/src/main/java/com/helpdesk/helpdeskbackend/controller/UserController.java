package com.helpdesk.helpdeskbackend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.helpdeskbackend.model.User;
import com.helpdesk.helpdeskbackend.service.UserService;
import com.helpdesk.helpdeskbackend.service.pojo.UpdateUserPassword;
import com.helpdesk.helpdeskbackend.service.pojo.UpdateUserStatus;
import com.helpdesk.helpdeskbackend.service.pojo.UserRegistration;
import com.helpdesk.helpdeskbackend.service.pojo.UserUpdateDetails;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;


	@GetMapping
	public List<User> users() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") long id) {
		return userService.getUserById(id);
	}

	@GetMapping("/{id}/form")
	public User editUserById(@PathVariable("id") long id) {
		return userService.getUserById(id);
	}

	@GetMapping("/form/{username}")
	public ResponseEntity<?> findUserByUsername(@PathVariable("username") String username) {
		User user = userService.findUserByUsername(username);
		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody UserRegistration userRegistration) {
		if (!userRegistration.getPassword().equals(userRegistration.getConfirmPassword())) {
			return new ResponseEntity<>("Passwords do not match", HttpStatus.NOT_ACCEPTABLE);
		} else if (userService.findUserByUsername(userRegistration.getUsername()) != null) {
			return new ResponseEntity<>("Username already in use", HttpStatus.NOT_ACCEPTABLE);
		}

		User user = new User(userRegistration.getRole(), userRegistration.getFirstName(),
				userRegistration.getLastName(), userRegistration.getEmail(), userRegistration.getUsername(),
				userRegistration.getPassword(), userRegistration.isActive());

		userService.save(user);
		log.info(user.toString());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}/update")
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UserUpdateDetails newUserData) {

		User updatingUser = userService.getUserById(id);

		// check if passed correct user id
		if (updatingUser == null) {
			return new ResponseEntity<>("User id not found", HttpStatus.NOT_ACCEPTABLE);
		}
		// check if username is taken
		else if (userService.findUserByUsername(newUserData.getUsername()) != null
				&& newUserData.getUsername() == updatingUser.getUsername()) {
			return new ResponseEntity<>("Username already in use", HttpStatus.NOT_ACCEPTABLE);
		}
		updatingUser = userService.updateUserData(updatingUser, newUserData);
		userService.save(updatingUser);
		return new ResponseEntity<>(updatingUser, HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{id}/statusupdate")
	public ResponseEntity<?> UpdateUserStatus(@PathVariable("id") long id, @RequestBody UpdateUserStatus statusUpdate) {

		User user = userService.getUserById(id);

		// check if passed wrong id
		if (user == null) {
			return new ResponseEntity<>("User id not found", HttpStatus.NOT_ACCEPTABLE);
		}
		user.setActive(statusUpdate.isStatus());
		userService.updateUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PatchMapping(value = "/{id}/passwordupdate")
	public ResponseEntity<?> UpdateUserpassword(@PathVariable("id") long id,
			@RequestBody UpdateUserPassword updateUserPassword) {

		User user = userService.getUserById(id);

		// check if passed wrong id
		if (user == null) {
			return new ResponseEntity<>("User id not found", HttpStatus.NOT_ACCEPTABLE);
		}

		// check if password and password confirmation are equals
		else if (!updateUserPassword.getPassword().equals(updateUserPassword.getConfirmPassword())) {
			return new ResponseEntity<>("Passwords do not match", HttpStatus.NOT_ACCEPTABLE);
		}

		// check if old password was passed correctly
		else if (!updateUserPassword.getOldPassword().equals(user.getPassword())) {
			return new ResponseEntity<>("Old password does not match", HttpStatus.NOT_ACCEPTABLE);
		}

		userService.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}