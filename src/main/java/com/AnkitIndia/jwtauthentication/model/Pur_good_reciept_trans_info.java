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
@Table(name="Pur_good_reciept_trans_info")
public class Pur_good_reciept_trans_info extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grn_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'",nullable=false)
	private String grn_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String trans_borne_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String mode_of_trans;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transporter_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transportation_rate;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String payment_mode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String payment_term;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bank_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String acc_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acc_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String branch;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String iban;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bic_swift_code;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="P_Ord_Rcv_Id")
	Pur_good_receipt pur_good_receipt;
	
}
