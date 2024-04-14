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
@Table(name="Pur_good_receipt_Business_Partner_details")
public class Pur_good_receipt_Business_Partner_details extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grn_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grn_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sp_name;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String sp_designation;
	
	private Long sp_phone;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sp_fax;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sp_email;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String sp_address;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_designation;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_name;
	
	private Long cp_phone;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_fax;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_email;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String cp_address;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="P_Ord_Rcv_Id")
	Pur_good_receipt pur_good_receipt;

}
