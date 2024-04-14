package com.AnkitIndia.jwtauthentication.responseDTO;

public class Cust_groupDTO {
	
	private String grp_code;
	
	private String grp_name;
	
	private String cp_code;
	
	private String company_id;
	
	private String parent_group;
	
	private String bus_part_name;
	
	private boolean bus_part_active;
	
	private String ctrl_acc;
	
	private String fin_year;
	
	public String getCp_code() {
		return cp_code;
	}

	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getParent_group() {
		return parent_group;
	}

	public void setParent_group(String parent_group) {
		this.parent_group = parent_group;
	}

	public String getBus_part_name() {
		return bus_part_name;
	}

	public void setBus_part_name(String bus_part_name) {
		this.bus_part_name = bus_part_name;
	}

	public boolean isBus_part_active() {
		return bus_part_active;
	}

	public void setBus_part_active(boolean bus_part_active) {
		this.bus_part_active = bus_part_active;
	}

	public String getGrp_code() {
		return grp_code;
	}

	public void setGrp_code(String grp_code) {
		this.grp_code = grp_code;
	}

	public String getGrp_name() {
		return grp_name;
	}

	public void setGrp_name(String grp_name) {
		this.grp_name = grp_name;
	}

	public String getCtrl_acc() {
		return ctrl_acc;
	}

	public void setCtrl_acc(String ctrl_acc) {
		this.ctrl_acc = ctrl_acc;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public Cust_groupDTO() {
		super();
	}

	public Cust_groupDTO(String grp_code, String grp_name, String cp_code, String company_id, String parent_group,
			String bus_part_name, boolean bus_part_active, String ctrl_acc, String fin_year) {
		super();
		this.grp_code = grp_code;
		this.grp_name = grp_name;
		this.cp_code = cp_code;
		this.company_id = company_id;
		this.parent_group = parent_group;
		this.bus_part_name = bus_part_name;
		this.bus_part_active = bus_part_active;
		this.ctrl_acc = ctrl_acc;
		this.fin_year = fin_year;
	}

	
	
}
