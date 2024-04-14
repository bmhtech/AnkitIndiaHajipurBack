package com.AnkitIndia.jwtauthentication.responseDTO;

public class EmployeeMasterDTOC {

	private String emp_id;
	
	private String emp_name;

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public EmployeeMasterDTOC(String emp_id, String emp_name) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
	}
	
	
}
