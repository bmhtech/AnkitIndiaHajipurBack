package com.AnkitIndia.jwtauthentication.responseDTO;

public class SubgroupmasterDTO {

	private String subgroupcode;  
	
	private String subgroupname;

	public String getSubgroupcode() {
		return subgroupcode;
	}

	public void setSubgroupcode(String subgroupcode) {
		this.subgroupcode = subgroupcode;
	}

	public String getSubgroupname() {
		return subgroupname;
	}

	public void setSubgroupname(String subgroupname) {
		this.subgroupname = subgroupname;
	}

	public SubgroupmasterDTO() {
		super();
	}

	public SubgroupmasterDTO(String subgroupcode, String subgroupname) {
		super();
		this.subgroupcode = subgroupcode;
		this.subgroupname = subgroupname;
	}
	
	
}
