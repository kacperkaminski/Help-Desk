package com.helpdesk.helpdeskbackend.service.pojo;

public class UpdateUserStatus {
	
	private boolean status;
	
	public UpdateUserStatus() {
	}
	
	public UpdateUserStatus(boolean status) {
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UpdateUserStatus [status=" + status + "]";
	}
	
	
}
