package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Production_planning_special_date_detailsDTO {

	private Long id;
	
	private String prod_plan_id;
	
	private String prod_plan_code;
	
	private String ppsplid;
	
	private int sl_no;
	
	private boolean checkbox;
	
	private String fromdate;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProd_plan_id() {
		return prod_plan_id;
	}

	public void setProd_plan_id(String prod_plan_id) {
		this.prod_plan_id = prod_plan_id;
	}

	public String getProd_plan_code() {
		return prod_plan_code;
	}

	public void setProd_plan_code(String prod_plan_code) {
		this.prod_plan_code = prod_plan_code;
	}

	public String getPpsplid() {
		return ppsplid;
	}

	public void setPpsplid(String ppsplid) {
		this.ppsplid = ppsplid;
	}
	
	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
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

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	
	
}
