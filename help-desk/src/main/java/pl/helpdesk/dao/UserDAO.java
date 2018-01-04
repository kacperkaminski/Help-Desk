package pl.helpdesk.dao;

import java.util.List;

import pl.helpdesk.model.User;

public interface UserDAO {

	public List<User> getAllUsers();

	public void saveUser(User theUser);

	public User getUser(int id);

	public void deleteUser(int id);

	public List<User> searchUsers(String theSearchName);

}
