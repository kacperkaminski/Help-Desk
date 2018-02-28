package com.helpdesk.helpdeskbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.helpdeskbackend.model.Role;
import com.helpdesk.helpdeskbackend.service.RoleService;

@CrossOrigin(origins = "*")
@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public List<Role> users() {
		return roleService.getAllRoles();
	}
}
