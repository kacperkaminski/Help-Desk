package com.helpdesk.helpdeskbackend.service.pojo;

public class UserDataToSend {

	private String username;
	private String firstName;
	private String lastName;
	private String role;
	private long id;

	public UserDataToSend() {
	}

	public UserDataToSend(String username, String firstName, String lastName, String role, long id) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
