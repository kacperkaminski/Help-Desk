package com.helpdesk.helpdeskbackend.service;

import java.util.List;

import com.helpdesk.helpdeskbackend.model.User;
import com.helpdesk.helpdeskbackend.service.pojo.UpdateUserStatus;
import com.helpdesk.helpdeskbackend.service.pojo.UserUpdateDetails;


public interface UserService {

	public List<User> getAllUsers();

	public User getUserById(long id);

	public User updateUser(User user);
	
	public User updateUserData(User updatingUser, UserUpdateDetails userData);
	
	public User updateUserStatus(long id, UpdateUserStatus statusUpdate);

	public User findUserByUsername(String username);

	public void save(User user);

}
