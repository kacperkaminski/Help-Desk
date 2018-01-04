package pl.helpdesk.dao;

import java.util.List;

import pl.helpdesk.model.UserGroup;

public interface UserGroupDAO {

	public List<UserGroup> getAllGroups();

	public void saveUserGroup(UserGroup userGroup);

	public UserGroup getUserGroup(int id);

	public void deleteUserGroup(int id);
}

