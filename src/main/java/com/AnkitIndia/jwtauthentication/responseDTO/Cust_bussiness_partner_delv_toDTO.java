package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Cust_bussiness_partner_delv_toDTO {

	private String cp_Id;
	
	private String company_id;
	
	private Long sl_no; 
	
	private String b_unit_name;
	
	private String contact_person;
	
	private String designation;
	
	private String address;
	
	private String city;
	
	private Long pincode;
	
	private Long phone; 
	
	private Long mobile;
	
	private String fax;
	
	private String email;
	
	private String transport_own;
	
	private String transporters;
	
	private String fin_year;
	
	private String modified_type;
	
	private String inserted_by;
	
	private String updated_by;
	
	private String ship_to;
	
	public String getShip_to() {
		return ship_to;
	}

	public void setShip_to(String ship_to) {
		this.ship_to = ship_to;
	}

	public String getCp_Id() {
		return cp_Id;
	}

	public void setCp_Id(String cp_Id) {
		this.cp_Id = cp_Id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public Long getSl_no() {
		return sl_no;
	}

	public void setSl_no(Long sl_no) {
		this.sl_no = sl_no;
	}

	public String getB_unit_name() {
		return b_unit_name;
	}

	public void setB_unit_name(String b_unit_name) {
		this.b_unit_name = b_unit_name;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTransport_own() {
		return transport_own;
	}

	public void setTransport_own(String transport_own) {
		this.transport_own = transport_own;
	}

	public String getTransporters() {
		return transporters;
	}

	public void setTransporters(String transporters) {
		this.transporters = transporters;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}
	
	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	
	public Cust_bussiness_partner_delv_toDTO() {
		super();
	}

	public Cust_bussiness_partner_delv_toDTO(String cp_Id, String company_id, Long sl_no, String b_unit_name,
			String contact_person, String designation, String address, String city, Long pincode, Long phone,
			Long mobile, String fax, String email, String transport_own, String transporters, String fin_year,
			String modified_type, String inserted_by, String updated_by) {
		super();
		this.cp_Id = cp_Id;
		this.company_id = company_id;
		this.sl_no = sl_no;
		this.b_unit_name = b_unit_name;
		this.contact_person = contact_person;
		this.designation = designation;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.phone = phone;
		this.mobile = mobile;
		/*this.fax = fax;*/
		this.email = email;
		this.transport_own = transport_own;
		this.transporters = transporters;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_by = inserted_by;
		this.updated_by = updated_by;
	}

		
	
	
}
