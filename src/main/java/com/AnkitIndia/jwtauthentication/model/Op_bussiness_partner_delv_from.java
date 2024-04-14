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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Op_bussiness_partner_delv_from")

public class Op_bussiness_partner_delv_from extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String contact_person;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String designation;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String address;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String city;
	
	private Long pincode;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String fax;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String email;
	
	private Long phone ;
	
	private Long  mobile;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "OP_Id")
    private Op_bussiness_partner op_bussiness_partner2;

}
