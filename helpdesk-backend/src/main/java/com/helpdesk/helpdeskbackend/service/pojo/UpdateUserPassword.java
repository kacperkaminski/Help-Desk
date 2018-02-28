package com.helpdesk.helpdeskbackend.service.pojo;

public class UpdateUserPassword {
	private String oldPassword;
	private String password;
	private String confirmPassword;

	public UpdateUserPassword() {

	}

	public UpdateUserPassword(String oldPassword, String password, String confirmPassword) {
		this.oldPassword = oldPassword;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
