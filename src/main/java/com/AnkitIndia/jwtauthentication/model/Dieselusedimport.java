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
@Table(name="Dieselusedimport")
@Data
@EqualsAndHashCode(callSuper=false)
public class Dieselusedimport extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dieselusedimportid;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String date;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String litter_opening;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String opening_percentage;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String use_litter;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String use_percentage;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String litter_balance;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String balance_percentage;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String hours;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String average;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String stock;
}
