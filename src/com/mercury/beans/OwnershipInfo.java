package com.mercury.beans;

import java.util.Set;

public class OwnershipInfo {
	private String message;
	private Set<Ownership> ownerships;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Set<Ownership> getOwnerships() {
		return ownerships;
	}
	public void setOwnerships(Set<Ownership> ownerships) {
		this.ownerships = ownerships;
	}
	
}
