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
@Table(name="Sales_credit_note_payment_dtls")
public class Sales_credit_note_payment_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String creditnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String creditnoteno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String mode_of_payment;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String payment_term;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bank_name;

	@Column(columnDefinition="varchar(100) default 'NA'")
	private String account_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String account_no; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String branch; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String iban; 
    
	@Column(columnDefinition="varchar(50) default 0")
	private String bic_swift_code;
    
	@Column(columnDefinition = "Double(10,2)")
	private double adv_payment;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String ifsc_code;
    
	@Column(columnDefinition = "Double(10,2)")
	private double cash_limit;
    
    private Long mobile;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "scn_id")
	private Sales_credit_note sales_credit_note;
}
