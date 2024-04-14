package com.AnkitIndia.jwtauthentication.responseDTO;

public class Production_planningDTO {
	
	private Long id;
	
	private String prod_plan_id;
	
	private String prod_plan_code;
    
	private String business_unit;
	
	private String business_unitname;
    
	private String pred_from;
    
	private String pred_to;
    
	private String prod_plan_desc;
	
	private String company_id;
	
	private String fin_year;
	
	private String username;
	
	private String modified_type;

	private boolean status;
	
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

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}
	
	public String getBusiness_unitname() {
		return business_unitname;
	}

	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}

	public String getPred_from() {
		return pred_from;
	}

	public void setPred_from(String pred_from) {
		this.pred_from = pred_from;
	}

	public String getPred_to() {
		return pred_to;
	}

	public void setPred_to(String pred_to) {
		this.pred_to = pred_to;
	}

	public String getProd_plan_desc() {
		return prod_plan_desc;
	}

	public void setProd_plan_desc(String prod_plan_desc) {
		this.prod_plan_desc = prod_plan_desc;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
