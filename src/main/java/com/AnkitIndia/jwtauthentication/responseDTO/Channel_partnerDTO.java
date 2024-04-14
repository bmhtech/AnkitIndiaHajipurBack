package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;

//@Entity
//@Table(name="Channel_partner")
public class Channel_partnerDTO {
	
	
	
	@Size(max = 50)
	private String channel_id;
	
	@Size(max = 50)
	private String channel_name;

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	

	
	
	

}
