package com.AnkitIndia.jwtauthentication.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="Broker_master_add")
public class Broker_master_add extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String website;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String country;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String state_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String state;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dist_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String dist;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String city_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String city;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String postid;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String post_office;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String pin;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String address1;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String address2;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String address3;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "b_Id")
	private Broker_master broker_master;

}
