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
@Table(name="Op_bussiness_partner_address")

public class Op_bussiness_partner_address extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String contact_person;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String designation;
	
	private Long phone;
	
	private Long mobile;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String fax;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String email;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String website;
	
	@Column(columnDefinition="varchar(70) default 'NA'")
	private String country;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String state;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String  district;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String city;
	
	private Long pincode;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String add1;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String add2;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String add3;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "OP_Id")
    private Op_bussiness_partner op_bussiness_partner;

}
