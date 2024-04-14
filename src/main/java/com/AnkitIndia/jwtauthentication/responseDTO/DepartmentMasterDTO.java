package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class DepartmentMasterDTO {
	
	private Long id;
	
	private String department_code;
	
	private String department_name;
	
	private boolean department_active;
	
	private String department_remarks;
	
	private String businessunit_code;
	
	private String businessunit_name;
	
	private String fin_year;
	
	private String company_id;
	
	private String username;
	
	private String modified_type;
	
	private LocalDateTime created_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	public DepartmentMasterDTO() {
		super();
	}

	public DepartmentMasterDTO(String department_code, String department_name, boolean department_active) {
		super();
		this.department_code = department_code;
		this.department_name = department_name;
		this.department_active = department_active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public boolean isDepartment_active() {
		return department_active;
	}

	public void setDepartment_active(boolean department_active) {
		this.department_active = department_active;
	}

	public String getDepartment_remarks() {
		return department_remarks;
	}

	public void setDepartment_remarks(String department_remarks) {
		this.department_remarks = department_remarks;
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

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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

	public LocalDateTime getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDateTime created_on) {
		this.created_on = created_on;
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
