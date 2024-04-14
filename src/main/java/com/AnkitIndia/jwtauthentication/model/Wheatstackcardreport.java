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
@Table(name="Wheatstackcardreport")
@Data
@EqualsAndHashCode(callSuper=false)
public class Wheatstackcardreport  extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wheatstackid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String godowncode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String godownname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stackno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String closed;
   
	@OneToMany(fetch = FetchType.LAZY,mappedBy="wheatstackcardreport",cascade = CascadeType.ALL)
	private Set<Wheatstackcardreportdetails> wheatstackcardreportdetails;
	
}
