package com.AnkitIndia.jwtauthentication.transResponseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

public class Broker_master_add_dtlsDTO {
	private Long id;
	
	private String broker_Id;
	
	private String broker_code;
	
	private String fin_year;
	
	private String company_id;
	
	private String contact_person;
	
	private String designation;
	
	private Long tell_no;
	
	private Long mob_no;
	
	private String fax_no;
	
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBroker_Id() {
		return broker_Id;
	}

	public void setBroker_Id(String broker_Id) {
		this.broker_Id = broker_Id;
	}

	public String getBroker_code() {
		return broker_code;
	}

	public void setBroker_code(String broker_code) {
		this.broker_code = broker_code;
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

	public Long getTell_no() {
		return tell_no;
	}

	public void setTell_no(Long tell_no) {
		this.tell_no = tell_no;
	}

	public Long getMob_no() {
		return mob_no;
	}

	public void setMob_no(Long mob_no) {
		this.mob_no = mob_no;
	}

	public String getFax_no() {
		return fax_no;
	}

	public void setFax_no(String fax_no) {
		this.fax_no = fax_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
