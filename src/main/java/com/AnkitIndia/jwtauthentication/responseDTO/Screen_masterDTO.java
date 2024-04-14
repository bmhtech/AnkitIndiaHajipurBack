package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;


public class Screen_masterDTO {
	
	
	@Size(max = 50)
	private String screen_id;
	
	@Size(max = 50)
	private String screen_type;
	
	@Size(max = 100)
	private String screen_name;

	public String getScreen_id() {
		return screen_id;
	}

	public void setScreen_id(String screen_id) {
		this.screen_id = screen_id;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getScreen_type() {
		return screen_type;
	}

	public void setScreen_type(String screen_type) {
		this.screen_type = screen_type;
	}

}
