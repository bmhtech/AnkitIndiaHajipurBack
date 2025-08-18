package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="Wheat_fumigation_details_allocate")
public class Wheat_fumigation_details_allocate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long details_table_id;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String company_id;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String fin_year;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;
	
	@Column(columnDefinition="varchar(20) default 'INSERTED'")
	private String modified_type;
	
	private LocalDateTime updated_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String updated_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String fumigation_id;
	
	@Column(columnDefinition="varchar(30) default '0'")
	private String allocate_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String allocate_status;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pcmw_sign_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supervisor_sign_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String lab_sign_name;
	
	@Column(columnDefinition="varchar(30) default '0'")
	private String status;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String manpower;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String degassing_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String degassing_time;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wheat_fumi_qc;
}