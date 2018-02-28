package com.helpdesk.helpdeskbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.helpdeskbackend.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
