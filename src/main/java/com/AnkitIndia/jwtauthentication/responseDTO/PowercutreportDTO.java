package com.AnkitIndia.jwtauthentication.responseDTO;

public class PowercutreportDTO {
	
	private Long id;
	
	private String business_unit;
	
	private String date;
	
	private String powercuttime;
	
	private String powerontime;
	
	private String diffpower;

	
	
	private String business_unitname;
	
	
	private String powerondate;


	public String getBusiness_unitname() {
		return business_unitname;
	}

	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPowercuttime() {
		return powercuttime;
	}

	public void setPowercuttime(String powercuttime) {
		this.powercuttime = powercuttime;
	}

	public String getPowerontime() {
		return powerontime;
	}

	public void setPowerontime(String powerontime) {
		this.powerontime = powerontime;
	}

	public String getDiffpower() {
		return diffpower;
	}

	public void setDiffpower(String diffpower) {
		this.diffpower = diffpower;
	}

	public String getPowerondate() {
		return powerondate;
	}

	public void setPowerondate(String powerondate) {
		this.powerondate = powerondate;
	}
	
	
	

}
