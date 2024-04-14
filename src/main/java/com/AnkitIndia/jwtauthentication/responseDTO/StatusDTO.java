package com.AnkitIndia.jwtauthentication.responseDTO;

public class StatusDTO {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public StatusDTO() {
		super();
	}

	public StatusDTO(String status) {
		super();
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusDTO [status=" + status + "]";
	}
	
}
