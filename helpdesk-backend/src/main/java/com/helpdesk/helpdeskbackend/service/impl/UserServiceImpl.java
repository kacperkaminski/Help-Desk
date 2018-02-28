package com.helpdesk.helpdeskbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.helpdeskbackend.model.User;
import com.helpdesk.helpdeskbackend.repository.UserRepository;
import com.helpdesk.helpdeskbackend.service.UserService;
import com.helpdesk.helpdeskbackend.service.pojo.UpdateUserStatus;
import com.helpdesk.helpdeskbackend.service.pojo.UserUpdateDetails;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User getUserById(long id) {
		return userRepository.getUserById(id);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUserStatus(long id, UpdateUserStatus statusUpdate) {
		User user = userRepository.getUserById(id);
		user.setActive(statusUpdate.isStatus());
		userRepository.save(user);
		return user;
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public User updateUserData(User updatingUser, UserUpdateDetails userData) {
		updatingUser.setRole(userData.getRole());
		updatingUser.setFirstName(userData.getFirstName());
		updatingUser.setLastName(userData.getLastName());
		updatingUser.setEmail(userData.getEmail());
		updatingUser.setUsername(userData.getUsername());
		updatingUser.setActive(userData.isActive());
		return updatingUser;
	}

}
