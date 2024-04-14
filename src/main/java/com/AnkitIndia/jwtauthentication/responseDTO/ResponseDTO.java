package com.AnkitIndia.jwtauthentication.responseDTO;

public class ResponseDTO {

	private String status;
	
	private String cancel_message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCancel_message() {
		return cancel_message;
	}

	public void setCancel_message(String cancel_message) {
		this.cancel_message = cancel_message;
	}
	
	public ResponseDTO() {
		super();
	}
	
	public ResponseDTO(String status, String cancel_message) {
		super();
		this.status = status;
		this.cancel_message = cancel_message;
	}

	@Override
	public String toString() {
		return "ResponseDTO [status=" + status + ", cancel_message=" + cancel_message + "]";
	}

	

}
