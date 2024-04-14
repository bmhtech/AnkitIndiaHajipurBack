package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Company_business_unit_details")
public class Company_business_unit_details extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date expiry_date;
	
	private String licence_name; 
	
	private String licence_no;
	
	private String remarks;
	
	private Long sln_no;
	
	private String businessunit_id;
	
	private String doc_directory;
	
	private String doc_name;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="C_B_U_Id")
	private Company_business_unit_master company_business_unit_master;

	
}
