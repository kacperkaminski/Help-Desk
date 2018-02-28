package com.helpdesk.helpdeskbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.helpdeskbackend.model.Role;
import com.helpdesk.helpdeskbackend.repository.RoleRepository;

@Service
public class RoleServiceImpl implements com.helpdesk.helpdeskbackend.service.RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAll().forEach(roles::add);
		return roles;
	}

}
