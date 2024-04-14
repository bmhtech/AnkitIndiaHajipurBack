package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.AnkitIndia.jwtauthentication.model.Groupmaster;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Parent_control", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name","branchcode"})
})
public class Parent_control {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String branchcode;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String category_id;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String name;                                
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String parent;    
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String code;    
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String parentcode;  
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_by;        
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_location;
	
	private LocalDateTime modified_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String modified_by;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String modified_location;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String fin_year;      
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String status; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="gr_id")
	@JsonManagedReference
	private Groupmaster groupmaster;

	public Parent_control() {
		super();
	}

	public Parent_control(long id, String branchcode, String category_id, String name, String parent, String code,
			String parentcode, LocalDateTime inserted_on, String inserted_by, String inserted_location,
			LocalDateTime modified_on, String modified_by, String modified_location, String fin_year, String status,
			Groupmaster groupmaster) {
		super();
		this.id = id;
		this.branchcode = branchcode;
		this.category_id = category_id;
		this.name = name;
		this.parent = parent;
		this.code = code;
		this.parentcode = parentcode;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.inserted_location = inserted_location;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.modified_location = modified_location;
		this.fin_year = fin_year;
		this.status = status;
		this.groupmaster = groupmaster;
	}

	public Parent_control(String branchcode, String category_id, String name, String parent, String code,
			String parentcode, LocalDateTime inserted_on, String inserted_by, String inserted_location,
			LocalDateTime modified_on, String modified_by, String modified_location, String fin_year, String status,
			String username) {
		super();
		this.branchcode = branchcode;
		this.category_id = category_id;
		this.name = name;
		this.parent = parent;
		this.code = code;
		this.parentcode = parentcode;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.inserted_location = inserted_location;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.modified_location = modified_location;
		this.fin_year = fin_year;
		this.status = status;
		this.username = username;
	}
	
	public Parent_control(String branchcode,  String name, String parent, String code,
			String fin_year) {
		super();
		this.branchcode = branchcode;
		this.name = name;
		this.parent = parent;
		this.code = code;
		this.fin_year = fin_year;
	}
	
	public Parent_control(String branchcode,  String name, String code,
			String fin_year) {
		super();
		this.branchcode = branchcode;
		this.name = name;
		this.code = code;
		this.fin_year = fin_year;
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

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
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

	public String getInserted_location() {
		return inserted_location;
	}

	public void setInserted_location(String inserted_location) {
		this.inserted_location = inserted_location;
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

	public String getModified_location() {
		return modified_location;
	}

	public void setModified_location(String modified_location) {
		this.modified_location = modified_location;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Groupmaster getGroupmaster() {
		return groupmaster;
	}

	public void setGroupmaster(Groupmaster groupmaster) {
		this.groupmaster = groupmaster;
	}
	
	
}
