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
@Table(name="Pur_Bill_Account_Info")
public class Pur_Bill_Account_Info extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pur_bill_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pur_bill_no;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String mode_of_pay;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pay_term;
	
	@Column(columnDefinition = "Double(10,2)")
	private double credit_lim; 
    
    @Column(columnDefinition = "varchar(100) default 'NA'")
	private String bankname;
    
    @Column(columnDefinition = "varchar(100) default 'NA'")
	private String accountholder;
    
    private long acc_no;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String ifsc;
    
    private long mobile;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String  iban; 
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String bic_swift_code;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String branch;
    
    @Column(columnDefinition = "varchar(20) default 'NA'")
	private String payment_date;

	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
	private Pur_Bill pur_Bill;
	
}
