package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;

public class Status_tableDTO {
	private Long id;
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
