package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name="Employee_master")
public class Employee_master {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String emp_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String emp_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String emp_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String country_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String state_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String city_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String dept_id;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String address;
	
	private Long mobile_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String email_id;
	
	@Column(columnDefinition="varchar(500) ")
	private String preference;
	/*@Column(columnDefinition="varchar(20) default '0'")
	private String pan_no;
	
	private Long aadhaar_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String uan_no;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String employee_category;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String employee_grade;*/
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String company_id;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String fin_year;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String username;
	
	@Column(columnDefinition="varchar(50) default 'INSERTED'")
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String deleted_by;
	
	//For Employee Signup
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String User_role_id;
	
    public String getUser_role_id() {
		return User_role_id;
	}

	public void setUser_role_id(String user_role_id) {
		User_role_id = user_role_id;
	}

	@Size(min = 3, max = 50)
    //@Transient
    private String emp_username;
    
    @Size(min = 6, max = 40)
    //@Transient
    private String password;
	
	@Transient
	private Set<User_role_id> role;
	
	//For authentication
	
	public Set<User_role_id> getRole() {
		return role;
	}
	
	public String getEmp_username() {
		return emp_username;
	}

	public void setEmp_username(String emp_username) {
		this.emp_username = emp_username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(Set<User_role_id> role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
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

	public Long getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(Long mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
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

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
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

	public LocalDateTime getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(LocalDateTime deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	
	
}
