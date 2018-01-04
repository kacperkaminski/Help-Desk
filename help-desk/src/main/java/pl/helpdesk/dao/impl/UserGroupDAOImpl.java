package pl.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.helpdesk.dao.UserGroupDAO;
import pl.helpdesk.model.UserGroup;

@Repository
public class UserGroupDAOImpl implements UserGroupDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserGroupDAOImpl() {
		
	}

	@Override
	public List<UserGroup> getAllGroups() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<UserGroup> theQuery = currentSession.createQuery("from UserGroup", UserGroup.class);
		List<UserGroup> userGroups = theQuery.getResultList();
		return userGroups;
	}

	@Override
	public void saveUserGroup(UserGroup userGroup) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(userGroup);		
	}

	@Override
	public UserGroup getUserGroup(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		UserGroup userGroup = currentSession.get(UserGroup.class, id);
		return userGroup;
	}

	@Override
	public void deleteUserGroup(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query theQuery = currentSession.createQuery("delete from UserGroup where id=:userGroupId");
		theQuery.setParameter("userGroupId",id);
		theQuery.executeUpdate();
	}

}
