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
@Table(name="Dailystockfinishgood")
@Data
@EqualsAndHashCode(callSuper=false)
public class Dailystockfinishgood extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dailystockid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String createdby;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String createdby_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approvedby;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approvedby_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	
	@Column(columnDefinition = "Double(10,2)")
	private double grandtotal;
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="dailystockfinishgood",cascade = CascadeType.ALL)
	private Set<Dailystockfinishgood_Dtls> dailystockfinishgood_Dtls;
	
	
	
	
	
	
	
	
}
