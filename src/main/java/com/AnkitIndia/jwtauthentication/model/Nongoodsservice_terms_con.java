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
@Table(name="Nongoodsservice_terms_con")

public class Nongoodsservice_terms_con extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String paymenttype;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String payment_mode;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String cash_limit;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tcs_applicable;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tcs_rate;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String payment_terms;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String bank_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String account_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String account_no;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String branch;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String ifsc;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String mobile;
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String iban;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String bic_swift_code;
 	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "nid")
    private Nongoodsservice nongoodsservice;
}
