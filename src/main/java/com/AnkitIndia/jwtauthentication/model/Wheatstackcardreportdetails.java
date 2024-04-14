package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="Wheatstackcardreportdetails")
@Data
@EqualsAndHashCode(callSuper=false)
public class Wheatstackcardreportdetails extends CommonProperties {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wheatstackid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String openingbags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String openingloosebags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String openingmt;
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String truckno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String variety;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String origin;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String receiptbags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String receiptloosebags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String receiptmt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String issuebags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String issueloosebags;
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String issuemt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String closingbags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String closingloosebags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String closingmt;
	
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String savedstatus;
	

	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "s_id")
    private Wheatstackcardreport wheatstackcardreport;
}
