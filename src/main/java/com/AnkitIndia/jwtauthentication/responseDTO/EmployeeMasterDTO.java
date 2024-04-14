package com.AnkitIndia.jwtauthentication.responseDTO;

public class EmployeeMasterDTO {
	
	private String emp_id;
	
	private String emp_name;
	
	private String state_id;
	
	private String city_id;
	
	private String dept_id;
	
	private String address;
	
	private String email_id;
	
	private String pan_no;
	
	private String uan_no;
	
	private String employee_category;
	
	private String employee_grade;
	
	private Long mobile_no;
	
	private Long aadhaar_no;
	
	

	public Long getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(Long mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Long getAadhaar_no() {
		return aadhaar_no;
	}

	public void setAadhaar_no(Long aadhaar_no) {
		this.aadhaar_no = aadhaar_no;
	}

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

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getUan_no() {
		return uan_no;
	}

	public void setUan_no(String uan_no) {
		this.uan_no = uan_no;
	}

	public String getEmployee_category() {
		return employee_category;
	}

	public void setEmployee_category(String employee_category) {
		this.employee_category = employee_category;
	}

	public String getEmployee_grade() {
		return employee_grade;
	}

	public void setEmployee_grade(String employee_grade) {
		this.employee_grade = employee_grade;
	}

	public EmployeeMasterDTO() {
		super();
	}

	public EmployeeMasterDTO(String emp_id, String emp_name, String state_id, String city_id, String dept_id,
			String address, String email_id, String pan_no, String uan_no, String employee_category,
			String employee_grade, Long mobile_no, Long aadhaar_no) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.state_id = state_id;
		this.city_id = city_id;
		this.dept_id = dept_id;
		this.address = address;
		this.email_id = email_id;
		this.pan_no = pan_no;
		this.uan_no = uan_no;
		this.employee_category = employee_category;
		this.employee_grade = employee_grade;
		this.mobile_no = mobile_no;
		this.aadhaar_no = aadhaar_no;
	}

	
	
	
}
