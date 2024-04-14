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

@Entity
@Table(name="Tax_code_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Tax_code_details  extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(columnDefinition="varchar(50) default '0'")
	private String tax_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tax_id;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String srno;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String tax_name;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double tax_rate;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double cess;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double cgst;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double cgst_act_val;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double sgst;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double sgst_act_val;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double igst;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double igst_act_val; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String input_ledger;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String output_ledger;
     
	@Column(columnDefinition="varchar(50) default '0'")
	private String igst_output_ledger;
     
	@Column(columnDefinition="varchar(50) default '0'")
	private String igst_input_ledger;
     
	@Column(columnDefinition="varchar(50) default '0'")
	private String cgst_output_ledger;
     
	@Column(columnDefinition="varchar(50) default '0'")
	private String cgst_input_ledger;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Tax_code_Id")
	Tax_code_master tax_code_master;

	
	

}
