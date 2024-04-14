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
@Table(name="Nongoodsservice_bank_dtls")
public class Nongoodsservice_bank_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String pay_mode;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String bank_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String account_no;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String account_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String branch;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String iban;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String bic_swift_code;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String ifsc_code;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String cash_limit;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String payment_term;
 	
 	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "nid")
    private Nongoodsservice nongoodsservice;

}
