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
@Table(name="Dailypowerreport")
@Data
@EqualsAndHashCode(callSuper=false)
public class Dailypowerreport extends CommonProperties{
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(columnDefinition="varchar(50) default 'NA'")
private String dailyreportid;

@Column(columnDefinition="varchar(50) default 'NA'")
private String business_unit;

@Column(columnDefinition="varchar(50) default 'NA'")
private String business_unitname;

@Column(columnDefinition="varchar(30) default 'NA'")
private String date;

@Column(columnDefinition="varchar(30) default 'NA'")
private String time;

@Column(columnDefinition="varchar(20) default 'NA'")
private String am_pm;

@Column(columnDefinition="varchar(20) default 'NA'")
private String mwh;

@Column(columnDefinition="varchar(20) default 'NA'")
private String mvah;

@Column(columnDefinition="varchar(20) default 'NA'")
private String differencemvah;

@Column(columnDefinition="varchar(20) default 'NA'")
private String differencemwh;

@Column(columnDefinition="varchar(20) default 'NA'")
private String unit;

}
