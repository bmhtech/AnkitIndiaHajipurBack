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
@Table(name="Pur_Enquiry_BPartner_Details")
public class Pur_Enquiry_BPartner_Details extends CommonProperties {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String enquiry_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String enquiry_no;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bp_name;
	
	//private Long bp_mobile;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_name;
	
	private Long cp_mobile;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String send_via;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Penq_Id")
    private Pur_Enquiry pur_Enquiry;
	
}
