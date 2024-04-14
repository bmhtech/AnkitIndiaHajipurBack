package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;
import java.util.Set;

public class Production_planning_periodic_date_detailsDTO {
	
	private Long id;
	
	private String prod_plan_id;
	
	private String prod_plan_code;
	
	private String ppd_id;
	
	private int sl_no;
	
	private boolean checkbox;
	
	private String fromdate;
	
	private String todate;
	
	private String shift[];
	
	private String shift1;
	
	private String shift2;
	
	private int nshift[];
	
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

	public String getPpd_id() {
		return ppd_id;
	}

	public void setPpd_id(String ppd_id) {
		this.ppd_id = ppd_id;
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

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}
	
	public String[] getShift() {
		return shift;
	}

	public void setShift(String[] shift) {
		this.shift = shift;
	}

	public String getShift1() {
		return shift1;
	}

	public void setShift1(String shift1) {
		this.shift1 = shift1;
	}
	
	public String getShift2() {
		return shift2;
	}

	public void setShift2(String shift2) {
		this.shift2 = shift2;
	}

	public int[] getNshift() {
		return nshift;
	}

	public void setNshift(int[] nshift) {
		this.nshift = nshift;
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
