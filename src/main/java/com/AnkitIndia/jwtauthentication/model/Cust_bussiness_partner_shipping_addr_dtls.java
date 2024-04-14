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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Cust_bussiness_partner_shipping_addr_dtls")
public class Cust_bussiness_partner_shipping_addr_dtls extends CommonProperties
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String cp_Id;
	
	private Long slno;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String shipping_name;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String country_shipping;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String state_shipping;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String dist_code; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String dist_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String city;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String pincode;
	
	@Column(columnDefinition="varchar(500) default 'NA'")
	private String address;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "cbp_Id")
	private Cust_bussiness_partner cust_bussiness_partner;
}
