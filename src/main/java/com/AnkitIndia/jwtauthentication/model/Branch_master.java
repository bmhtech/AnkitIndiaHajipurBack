package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Branch_master")
public class Branch_master {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;  
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String branchcode;  
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String branchname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String parent_branchname;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String parent_branchcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String state;    
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String city;     
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String contact_number;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String gstn_number;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_by;
	
	private LocalDateTime modified_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String modified_by;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String fin_year; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;
	
	public Branch_master() {
		// TODO Auto-generated constructor stub
	}

	public Branch_master(long id, String branchcode, String branchname, String parent_branchname,
			String parent_branchcode, String state, String city, String contact_number, String gstn_number,
			String modified_type, LocalDateTime inserted_on, String inserted_by, LocalDateTime modified_on,
			String modified_by, String fin_year, String username) {
		super();
		this.id = id;
		this.branchcode = branchcode;
		this.branchname = branchname;
		this.parent_branchname = parent_branchname;
		this.parent_branchcode = parent_branchcode;
		this.state = state;
		this.city = city;
		this.contact_number = contact_number;
		this.gstn_number = gstn_number;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.fin_year = fin_year;
		this.username = username;
	}

	public Branch_master(String branchcode, String branchname, String parent_branchname, String parent_branchcode,
			String state, String city, String contact_number, String gstn_number, String modified_type,
			LocalDateTime inserted_on, String inserted_by, LocalDateTime modified_on, String modified_by,
			String fin_year, String username) {
		super();
		this.branchcode = branchcode;
		this.branchname = branchname;
		this.parent_branchname = parent_branchname;
		this.parent_branchcode = parent_branchcode;
		this.state = state;
		this.city = city;
		this.contact_number = contact_number;
		this.gstn_number = gstn_number;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.fin_year = fin_year;
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getParent_branchname() {
		return parent_branchname;
	}

	public void setParent_branchname(String parent_branchname) {
		this.parent_branchname = parent_branchname;
	}

	public String getParent_branchcode() {
		return parent_branchcode;
	}

	public void setParent_branchcode(String parent_branchcode) {
		this.parent_branchcode = parent_branchcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getGstn_number() {
		return gstn_number;
	}

	public void setGstn_number(String gstn_number) {
		this.gstn_number = gstn_number;
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

	public LocalDateTime getModified_on() {
		return modified_on;
	}

	public void setModified_on(LocalDateTime modified_on) {
		this.modified_on = modified_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
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

	@Override
	public String toString() {
		return "Branch_master [id=" + id + ", branchcode=" + branchcode + ", branchname=" + branchname
				+ ", parent_branchname=" + parent_branchname + ", parent_branchcode=" + parent_branchcode + ", state="
				+ state + ", city=" + city + ", contact_number=" + contact_number + ", gstn_number=" + gstn_number
				+ ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ ", modified_on=" + modified_on + ", modified_by=" + modified_by + ", fin_year=" + fin_year
				+ ", username=" + username + "]";
	}
	
}
