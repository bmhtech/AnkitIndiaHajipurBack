package com.AnkitIndia.jwtauthentication.responseDTO;

public class Channel_customer_masterDTO {

	private String channel_custid;
	
	private String channel_desc;
	
	private String custid;
	
	private String channeltype;
	
	private String group_type;
	
	private String group_type_name;
	
	private boolean terminate;

	public String getGroup_type() {
		return group_type;
	}

	public void setGroup_type(String group_type) {
		this.group_type = group_type;
	}

	public String getGroup_type_name() {
		return group_type_name;
	}

	public void setGroup_type_name(String group_type_name) {
		this.group_type_name = group_type_name;
	}

	public boolean isTerminate() {
		return terminate;
	}

	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}

	public String getChannel_custid() {
		return channel_custid;
	}

	public void setChannel_custid(String channel_custid) {
		this.channel_custid = channel_custid;
	}

	public String getChannel_desc() {
		return channel_desc;
	}

	public void setChannel_desc(String channel_desc) {
		this.channel_desc = channel_desc;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getChanneltype() {
		return channeltype;
	}

	public void setChanneltype(String channeltype) {
		this.channeltype = channeltype;
	}
	
	
}
