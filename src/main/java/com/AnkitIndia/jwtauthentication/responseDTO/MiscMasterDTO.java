package com.AnkitIndia.jwtauthentication.responseDTO;

public class MiscMasterDTO {
	
	private String mastertype_code;
	
	private String mastertype_name;
	
	private String description_head;
	
	private boolean mastertype_active;
	
	private String mastertype_remarks;
	
	private String businessunit_code;
	
	private String businessunit_name;

	public String getMastertype_code() {
		return mastertype_code;
	}

	public void setMastertype_code(String mastertype_code) {
		this.mastertype_code = mastertype_code;
	}

	public String getMastertype_name() {
		return mastertype_name;
	}

	public void setMastertype_name(String mastertype_name) {
		this.mastertype_name = mastertype_name;
	}

	public String getDescription_head() {
		return description_head;
	}

	public void setDescription_head(String description_head) {
		this.description_head = description_head;
	}

	public boolean isMastertype_active() {
		return mastertype_active;
	}

	public void setMastertype_active(boolean mastertype_active) {
		this.mastertype_active = mastertype_active;
	}

	public String getMastertype_remarks() {
		return mastertype_remarks;
	}

	public void setMastertype_remarks(String mastertype_remarks) {
		this.mastertype_remarks = mastertype_remarks;
	}

	public String getBusinessunit_code() {
		return businessunit_code;
	}

	public void setBusinessunit_code(String businessunit_code) {
		this.businessunit_code = businessunit_code;
	}

	public String getBusinessunit_name() {
		return businessunit_name;
	}

	public void setBusinessunit_name(String businessunit_name) {
		this.businessunit_name = businessunit_name;
	}

	public MiscMasterDTO() {
		super();
	}

	public MiscMasterDTO(String mastertype_code, String mastertype_name, String description_head,
			boolean mastertype_active, String mastertype_remarks, String businessunit_code, String businessunit_name) {
		super();
		this.mastertype_code = mastertype_code;
		this.mastertype_name = mastertype_name;
		this.description_head = description_head;
		this.mastertype_active = mastertype_active;
		this.mastertype_remarks = mastertype_remarks;
		this.businessunit_code = businessunit_code;
		this.businessunit_name = businessunit_name;
	}
	
	
}
