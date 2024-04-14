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
@Table(name="Pur_debit_note_account_info")
public class Pur_debit_note_account_info extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String debitnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String  debitnoteno;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String mode_of_pay;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String pay_term;
	
	@Column(columnDefinition = "Double(10,2)")
	private double credit_lim; 
    
    @Column(columnDefinition = "varchar(100) default '0'")
	private String bankname;
    
    @Column(columnDefinition = "varchar(100) default '0'")
	private String accountholder;
    
    private long acc_no;
    
    @Column(columnDefinition = "varchar(50) default '0'")
	private String ifsc;
    
    private long mobile;
    
    @Column(columnDefinition = "varchar(50) default '0'")
	private String  iban; 
    
    @Column(columnDefinition = "varchar(50) default '0'")
	private String bic_swift_code;
    
    @Column(columnDefinition = "varchar(100) default '0'")
	private String branch; 
	
		@OneToOne(cascade= CascadeType.ALL)
	    @JoinColumn(name = "scn_Id")
		private Pur_debit_note pur_debit_note;
		
}
