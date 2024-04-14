package com.AnkitIndia.jwtauthentication.responseDTO;

public class Powercutreport_Pagination_DTO {

	
    private Long id;
	
    private String powercutid;
    
	private String business_unit;
	
	private String powercutdate;
	
	private String powercuttime;
	
	private String powerondate;
	
	private String powerontime;
	
	private String diffpower;

	private String business_unitname;

	private String remarks;
	
	private String modified_type;

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPowercutid() {
		return powercutid;
	}



	public void setPowercutid(String powercutid) {
		this.powercutid = powercutid;
	}



	public String getBusiness_unit() {
		return business_unit;
	}



	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}



	public String getPowercutdate() {
		return powercutdate;
	}



	public void setPowercutdate(String powercutdate) {
		this.powercutdate = powercutdate;
	}



	public String getPowercuttime() {
		return powercuttime;
	}



	public void setPowercuttime(String powercuttime) {
		this.powercuttime = powercuttime;
	}



	public String getPowerondate() {
		return powerondate;
	}



	public void setPowerondate(String powerondate) {
		this.powerondate = powerondate;
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



	public String getBusiness_unitname() {
		return business_unitname;
	}



	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}



	public String getModified_type() {
		return modified_type;
	}



	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}


	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public Powercutreport_Pagination_DTO(Long id, String powercutid, String business_unit, String powercutdate,
			String powercuttime, String powerondate, String powerontime, String diffpower, String business_unitname,
			String remarks, String modified_type) {
		super();
		this.id = id;
		this.powercutid = powercutid;
		this.business_unit = business_unit;
		this.powercutdate = powercutdate;
		this.powercuttime = powercuttime;
		this.powerondate = powerondate;
		this.powerontime = powerontime;
		this.diffpower = diffpower;
		this.business_unitname = business_unitname;
		this.remarks = remarks;
		this.modified_type = modified_type;
	}

	
	
	
}
