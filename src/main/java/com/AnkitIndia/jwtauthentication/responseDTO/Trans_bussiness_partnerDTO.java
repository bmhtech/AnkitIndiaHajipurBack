package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;

public class Trans_bussiness_partnerDTO {

	private String bp_Id;
	
	private String bp_code;
	
	private String company_id;
	
	private String bp_type;
	
	private String bp_name;
	
	private String alt_name;
	
	private boolean bp_active;
	
	private String group_type;
	
	private String sub_group_type;
	
	private String trans_currency;
	
	private boolean block_active;
	
	private boolean pak_mat_replc;
	
	private boolean broker_status;
	
	private String constitution;

	private String ssi_app;

	private String ssi_regno;
	
	private int export;	
	
	private String response;

	public String getBp_Id() {
		return bp_Id;
	}

	public void setBp_Id(String bp_Id) {
		this.bp_Id = bp_Id;
	}

	public String getBp_code() {
		return bp_code;
	}

	public void setBp_code(String bp_code) {
		this.bp_code = bp_code;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getBp_type() {
		return bp_type;
	}

	public void setBp_type(String bp_type) {
		this.bp_type = bp_type;
	}

	public String getBp_name() {
		return bp_name;
	}

	public void setBp_name(String bp_name) {
		this.bp_name = bp_name;
	}

	public String getAlt_name() {
		return alt_name;
	}

	public void setAlt_name(String alt_name) {
		this.alt_name = alt_name;
	}

	public boolean isBp_active() {
		return bp_active;
	}

	public void setBp_active(boolean bp_active) {
		this.bp_active = bp_active;
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

	public boolean isPak_mat_replc() {
		return pak_mat_replc;
	}

	public void setPak_mat_replc(boolean pak_mat_replc) {
		this.pak_mat_replc = pak_mat_replc;
	}

	public boolean isBroker_status() {
		return broker_status;
	}

	public void setBroker_status(boolean broker_status) {
		this.broker_status = broker_status;
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
	

}
