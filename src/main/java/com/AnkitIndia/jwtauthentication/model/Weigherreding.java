package com.AnkitIndia.jwtauthentication.model;

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
@Table(name="Weigherreding")
public class Weigherreding extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String weigherredingid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String msdtopen;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String msdtclose;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String msdtbalance;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String cleaningopen;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String cleaningclose;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String cleaningbalance;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String b1weigheropen;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String b1weigherclose;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String b1weigherbalance;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String branweigheropen;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String branweigherclose;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String branweigherbalance;
	
}
