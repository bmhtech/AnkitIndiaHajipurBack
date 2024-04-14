package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="Pur_debit_note")
public class Pur_debit_note extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String debitnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String debitnoteno;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String debitnotetype;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String debitnotedate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String businessunit;
	 
	@Column(columnDefinition="varchar(100) default 0")
	private String businessunitname;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String supplier_id;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String supplier_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ser_item_type;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String ser_item_subtype;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String ser_item_subtypename;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String created_by;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String truck_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String vehicleno;

	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double item_total;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_total_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double discount;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String discount_gl_ac;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String qc_deduction;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double net_amt;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String net_amt_gl_ac ;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String qc_deduction_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double amt_after_deduction;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String amt_after_deduction_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double add_tax;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String add_tax_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double post_tax_amt;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String post_tax_amt_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double other_charges;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String other_charges_gl_ac;
    
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double payable_amt;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String payable_amt_gl_ac;
    
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double add1;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String add1_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double add2;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String add2_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double roundoff_amt;

	@Column(columnDefinition="varchar(50) default 0")
	private String roundoff_gl_ac;
    
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double payable_party;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String payable_party_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double already_paid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String already_paid_gl_ac;
    
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double net_payable_party;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String net_payable_party_gl_ac;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String referance_id;
	
	@Column(columnDefinition="varchar(500) default 0")
	private String remarks;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_debit_note",cascade = CascadeType.ALL)
	private Set<Pur_debit_note_item_details> pur_debit_note_item_details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_debit_note",cascade = CascadeType.ALL)
	private Set<Pur_debit_note_musterroll_details> pur_debit_note_musterroll_details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_debit_note",cascade = CascadeType.ALL)
	private Set<Pur_debit_note_broker_details> pur_debit_note_broker_details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_debit_note",cascade = CascadeType.ALL)
	private Set<Pur_debit_note_docs> pur_debit_note_docs;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_debit_note",cascade =CascadeType.ALL)
	private Pur_debit_note_tax_info pur_debit_note_tax_info;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_debit_note",cascade =CascadeType.ALL)
	private Pur_debit_note_bp_details pur_debit_note_bp_details;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_debit_note",cascade =CascadeType.ALL)
	private Pur_debit_note_account_info pur_debit_note_account_info;
	
}
