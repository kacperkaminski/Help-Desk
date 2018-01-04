package pl.helpdesk.service;

import java.util.List;

import pl.helpdesk.model.UserGroup;

public interface UserGroupService {
	
	public List<UserGroup> getAllGroups();

	public void saveUserGroup(UserGroup userGroup);

	public UserGroup getUserGroup(int id);

	public void deleteUserGroup(int id);
}
