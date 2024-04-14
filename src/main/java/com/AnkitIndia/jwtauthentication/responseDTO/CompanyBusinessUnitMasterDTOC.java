package com.AnkitIndia.jwtauthentication.responseDTO;

public class CompanyBusinessUnitMasterDTOC {

	private String businessunit_id;
	
	private String businessunit_name;

	public String getBusinessunit_id() {
		return businessunit_id;
	}

	public void setBusinessunit_id(String businessunit_id) {
		this.businessunit_id = businessunit_id;
	}

	public String getBusinessunit_name() {
		return businessunit_name;
	}

	public void setBusinessunit_name(String businessunit_name) {
		this.businessunit_name = businessunit_name;
	}

	public CompanyBusinessUnitMasterDTOC(String businessunit_id, String businessunit_name) {
		super();
		this.businessunit_id = businessunit_id;
		this.businessunit_name = businessunit_name;
	}
}
