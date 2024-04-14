package com.AnkitIndia.jwtauthentication.responseDTO;


public class Channel_partner_detailsDTO {

	
	private String channel_id;
	
	private String channel_code;
	
	private int sl_no;
	
	private String cp_name;
	
	private boolean cp_active;
	
	private String cp_remarks;

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_code() {
		return channel_code;
	}

	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public boolean isCp_active() {
		return cp_active;
	}

	public void setCp_active(boolean cp_active) {
		this.cp_active = cp_active;
	}

	public String getCp_remarks() {
		return cp_remarks;
	}

	public void setCp_remarks(String cp_remarks) {
		this.cp_remarks = cp_remarks;
	}
	
}
