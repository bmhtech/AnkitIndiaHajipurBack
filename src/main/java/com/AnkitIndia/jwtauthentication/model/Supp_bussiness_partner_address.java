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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Supp_bussiness_partner_address")
public class Supp_bussiness_partner_address extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String bp_Id;
		
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
	private String district;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String city_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String city;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String postid;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String post_office;
	
	private Long pincode; 
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String add1;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String add2;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String add3;
	

	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "BPart_Id")
    private Supp_bussiness_partner supp_bussiness_partner;


}
