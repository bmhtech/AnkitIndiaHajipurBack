package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;

public class Cust_bussiness_partnerDTO {

	private String cp_Id;
	
	private String cp_code;
	
	private String company_id;
	
	private String cp_type;
	
	private String cp_name;
	
	private String alt_name;
	
	private boolean cp_active;  
	
	private String group_type;
	
	private String sub_group_type;
	
	private String trans_currency;
	
	private boolean block_active; 
	
	private String reason;
	
	private boolean copy_bp_addr;
	
	private String business_unit;
	
	private String constitution;

	private String ssi_app;

	private String ssi_regno;
	
	private boolean broker_status;
	
	private int export;	
	
	private String response;
	
	private String print_to_name;
	
	private boolean saleclosed;
	
	public String getPrint_to_name() {
		return print_to_name;
	}

	public void setPrint_to_name(String print_to_name) {
		this.print_to_name = print_to_name;
	}

	//For Group Name Show
	private String group_name;
	

	public String getCp_Id() {
		return cp_Id;
	}

	public void setCp_Id(String cp_Id) {
		this.cp_Id = cp_Id;
	}

	public int getExport() {
		return export;
	}

	public void setExport(int export) {
		this.export = export;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

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

	public String getCp_type() {
		return cp_type;
	}

	public void setCp_type(String cp_type) {
		this.cp_type = cp_type;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public String getAlt_name() {
		return alt_name;
	}

	public void setAlt_name(String alt_name) {
		this.alt_name = alt_name;
	}

	public boolean isCp_active() {
		return cp_active;
	}

	public void setCp_active(boolean cp_active) {
		this.cp_active = cp_active;
	}

	public String getGroup_type() {
		return group_type;
	}

	public void setGroup_type(String group_type) {
		this.group_type = group_type;
	}

	public String getSub_group_type() {
		return sub_group_type;
	}

	public void setSub_group_type(String sub_group_type) {
		this.sub_group_type = sub_group_type;
	}

	public String getTrans_currency() {
		return trans_currency;
	}

	public void setTrans_currency(String trans_currency) {
		this.trans_currency = trans_currency;
	}

	public boolean isBlock_active() {
		return block_active;
	}

	public void setBlock_active(boolean block_active) {
		this.block_active = block_active;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isCopy_bp_addr() {
		return copy_bp_addr;
	}

	public void setCopy_bp_addr(boolean copy_bp_addr) {
		this.copy_bp_addr = copy_bp_addr;
	}
	
	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}
	
	public boolean isBroker_status() {
		return broker_status;
	}

	public void setBroker_status(boolean broker_status) {
		this.broker_status = broker_status;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public String getSsi_app() {
		return ssi_app;
	}

	public void setSsi_app(String ssi_app) {
		this.ssi_app = ssi_app;
	}

	public String getSsi_regno() {
		return ssi_regno;
	}

	public void setSsi_regno(String ssi_regno) {
		this.ssi_regno = ssi_regno;
	}

	public boolean isSaleclosed() {
		return saleclosed;
	}

	public void setSaleclosed(boolean saleclosed) {
		this.saleclosed = saleclosed;
	}

	public Cust_bussiness_partnerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cust_bussiness_partnerDTO(String cp_Id, String cp_code, String company_id, String cp_type, String cp_name,
			String alt_name, boolean cp_active, String group_type, String sub_group_type, String trans_currency,
			boolean block_active, String reason, boolean copy_bp_addr, String business_unit, String constitution,
			String ssi_app, String ssi_regno, boolean broker_status, int export, String response, String print_to_name,
			boolean saleclosed, String group_name) {
		super();
		this.cp_Id = cp_Id;
		this.cp_code = cp_code;
		this.company_id = company_id;
		this.cp_type = cp_type;
		this.cp_name = cp_name;
		this.alt_name = alt_name;
		this.cp_active = cp_active;
		this.group_type = group_type;
		this.sub_group_type = sub_group_type;
		this.trans_currency = trans_currency;
		this.block_active = block_active;
		this.reason = reason;
		this.copy_bp_addr = copy_bp_addr;
		this.business_unit = business_unit;
		this.constitution = constitution;
		this.ssi_app = ssi_app;
		this.ssi_regno = ssi_regno;
		this.broker_status = broker_status;
		this.export = export;
		this.response = response;
		this.print_to_name = print_to_name;
		this.saleclosed = saleclosed;
		this.group_name = group_name;
	}
	
	

	
	
}
