package com.AnkitIndia.jwtauthentication.responseDTO;

public class Reason_masterDTO {
	
	private String reason_id;
	
	private String screen_code;
	
	private String reason;

	public String getReason_id() {
		return reason_id;
	}

	public void setReason_id(String reason_id) {
		this.reason_id = reason_id;
	}
	
	public String getScreen_code() {
		return screen_code;
	}

	public void setScreen_code(String screen_code) {
		this.screen_code = screen_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Reason_masterDTO() {
		super();
	}

	public Reason_masterDTO(String reason_id, String screen_code, String reason) {
		super();
		this.reason_id = reason_id;
		this.screen_code = screen_code;
		this.reason = reason;
	}
	
}
