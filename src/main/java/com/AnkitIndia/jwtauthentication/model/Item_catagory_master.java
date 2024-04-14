package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name="Item_catagory_master", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"catagory_name"}),
        @UniqueConstraint(columnNames = {"catagory_id"}),
        @UniqueConstraint(columnNames = {"catagory_code"})
    })
public class Item_catagory_master {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=50)
	private String catagory_id; 
	
	@Size(max = 50)
	private String catagory_code;
	
	@Size(max = 50)
	private String company_id;
	
	@Size(max = 50)
	private String catagory_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  item_active;
	
	@Size(max = 100)
	private String item_type;
	
	@Size(max = 20)
	private String fin_year;
	
	@Size(max = 50)
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Size(max = 50)
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Size(max = 50)
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	@Size(max = 50)
	private String deleted_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCatagory_id() {
		return catagory_id;
	}

	public void setCatagory_id(String catagory_id) {
		this.catagory_id = catagory_id;
	}

	public String getCatagory_code() {
		return catagory_code;
	}

	public void setCatagory_code(String catagory_code) {
		this.catagory_code = catagory_code;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCatagory_name() {
		return catagory_name;
	}

	public void setCatagory_name(String catagory_name) {
		this.catagory_name = catagory_name;
	}

	public boolean isItem_active() {
		return item_active;
	}

	public void setItem_active(boolean item_active) {
		this.item_active = item_active;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}      
	
                  

}
