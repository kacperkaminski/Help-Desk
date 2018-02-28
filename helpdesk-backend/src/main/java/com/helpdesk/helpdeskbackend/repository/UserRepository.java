package com.helpdesk.helpdeskbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpdesk.helpdeskbackend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
			
	@Query("FROM User WHERE id=:userId")
	public User getUserById(@Param("userId") long userId);

	@Query("FROM User WHERE username LIKE :username")
	public User findUserByUsername(@Param("username")String username);
	
}
