package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="Powercutreport")
@Data
@EqualsAndHashCode(callSuper=false)
public class Powercutreport extends CommonProperties{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String powercutid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String powercutdate;
	

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String powercuttime;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String powerondate;
	
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String powerontime;
	

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String diffpower;
	
	@Column(columnDefinition="varchar(70) default 'NA'")
	private String remarks;


}
