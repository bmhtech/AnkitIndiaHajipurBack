package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="Gateinoutregister")
@Data
@EqualsAndHashCode(callSuper=false)
public class Gateinoutregister extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gateinoutregisterid;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String reg_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dept;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String intime;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String outtime;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String purpose;
	
	
	
}
