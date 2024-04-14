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
@Table(name="Cust_bussiness_partner_address_dtls")
public class Cust_bussiness_partner_address_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String cp_Id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String contact_person;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String designation;
	
	private Long phone;
	
	private Long mobile;  
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String fax; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String email;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "cbp_Id")
	private Cust_bussiness_partner cust_bussiness_partner;

	
}
