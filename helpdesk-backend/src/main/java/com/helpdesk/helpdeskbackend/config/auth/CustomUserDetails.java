package com.helpdesk.helpdeskbackend.config.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.helpdesk.helpdeskbackend.model.Role;
import com.helpdesk.helpdeskbackend.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 8029000558994462824L;
	private Collection<? extends GrantedAuthority> authorities;
	private String password;
	private String username;
	private boolean active;
	
	public CustomUserDetails(User user) {
		List<Role> roles = new ArrayList<Role>();;
		roles.add(user.getRole());
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = translate(roles);
    }

	private Collection<? extends GrantedAuthority> translate(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			String name = role.getName().toUpperCase();
			authorities.add(new SimpleGrantedAuthority(name));
		}
		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}