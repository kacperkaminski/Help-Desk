package com.helpdesk.helpdeskbackend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.helpdeskbackend.model.User;
import com.helpdesk.helpdeskbackend.service.UserService;
import com.helpdesk.helpdeskbackend.service.pojo.UserDataToSend;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {

	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/logouts")
	public void logout(@RequestBody String accessToken) {
		tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
	}
	
	@GetMapping(value="/user")
	public UserDataToSend user(Principal principal) {
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    UserDataToSend userDataToSend = new UserDataToSend();
	    User user = userService.findUserByUsername(username);
	    userDataToSend.setFirstName(user.getFirstName());
	    userDataToSend.setId(user.getId());
	    userDataToSend.setLastName(user.getLastName());
	    userDataToSend.setUsername(username);
	    userDataToSend.setRole(user.getRole().getName());
	    return userDataToSend;
	}
}
