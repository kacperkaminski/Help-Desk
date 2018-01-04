package pl.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.helpdesk.dao.UserGroupDAO;
import pl.helpdesk.model.UserGroup;
import pl.helpdesk.service.UserGroupService;

@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupDAO userGroupDAO;

	@Override
	@Transactional
	public List<UserGroup> getAllGroups() {
		return userGroupDAO.getAllGroups();
	}

	@Override
	@Transactional
	public void saveUserGroup(UserGroup userGroup) {
		userGroupDAO.saveUserGroup(userGroup);
	}

	@Override
	@Transactional
	public UserGroup getUserGroup(int id) {
		return userGroupDAO.getUserGroup(id);
	}

	@Override
	@Transactional
	public void deleteUserGroup(int id) {
		userGroupDAO.deleteUserGroup(id);

	}

}
