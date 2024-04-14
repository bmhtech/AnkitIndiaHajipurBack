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
@Table(name="Gatepassregister")
@Data
@EqualsAndHashCode(callSuper=false)
public class Gatepassregister extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gatepassregisterid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gp_slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gp_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String paidnonpaid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String particulars;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String amount;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approvedby;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approvedby_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String indate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String remarks;
	
}
