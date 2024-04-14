package com.AnkitIndia.jwtauthentication.responseDTO;

public class Invoice_typeDTO {
	
	private Long id; 
	
	private String company_id;
	
	private String invtype_id;
	
	private String invtype_code;
	
	private String invtype_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getInvtype_id() {
		return invtype_id;
	}

	public void setInvtype_id(String invtype_id) {
		this.invtype_id = invtype_id;
	}

	public String getInvtype_code() {
		return invtype_code;
	}

	public void setInvtype_code(String invtype_code) {
		this.invtype_code = invtype_code;
	}

	public String getInvtype_name() {
		return invtype_name;
	}

	public void setInvtype_name(String invtype_name) {
		this.invtype_name = invtype_name;
	}

	public Invoice_typeDTO() {
		super();
	}

	public Invoice_typeDTO(Long id, String company_id, String invtype_id, String invtype_code, String invtype_name) {
		super();
		this.id = id;
		this.company_id = company_id;
		this.invtype_id = invtype_id;
		this.invtype_code = invtype_code;
		this.invtype_name = invtype_name;
	}
	
	
}
