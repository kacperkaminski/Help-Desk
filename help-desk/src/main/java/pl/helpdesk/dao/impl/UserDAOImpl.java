package pl.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.helpdesk.dao.UserDAO;
import pl.helpdesk.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl() {

	}

	@Override
	public List<User> getAllUsers() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User order by lastName", User.class);
		List<User> users = theQuery.getResultList();
		return users;
	}

	@Override
	public void saveUser(User theUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theUser);

	}

	@Override
	public User getUser(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		User theUser = currentSession.get(User.class, id);
		return theUser;
	}

	@Override
	public void deleteUser(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query theQuery = currentSession.createQuery("delete from User where id=:userId");
		theQuery.setParameter("userId", id);
		theQuery.executeUpdate();
	}

	@Override
	public List<User> searchUsers(String theSearchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query theQuery = null;

		if (theSearchName != null && theSearchName.trim().length() > 0) {
			theQuery = currentSession.createQuery("from User where lower(firstName) like :theName or lower(lastName) like :theName",User.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			theQuery = currentSession.createQuery("from User", User.class);
		}
		
		@SuppressWarnings("unchecked")
		List<User> users = theQuery.getResultList();
		return users;
	}

}
