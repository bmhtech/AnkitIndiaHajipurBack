package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

public class Trans_bussiness_partner_statutoryDTO {
private boolean registered;
	
	private String pan_no;
	
	private String gst_no;
	
	private String cin_no;
	
	private String tan_no;
	
	private String fin_year;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	public String getCin_no() {
		return cin_no;
	}

	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
	}

	public String getTan_no() {
		return tan_no;
	}

	public void setTan_no(String tan_no) {
		this.tan_no = tan_no;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}
	
	
}
