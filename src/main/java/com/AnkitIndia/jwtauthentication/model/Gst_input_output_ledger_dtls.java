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
@Table(name="Gst_input_output_ledger_dtls")
public class Gst_input_output_ledger_dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String taxtype_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String cgst_input_ledger;
	 
	@Column(columnDefinition="varchar(50) default '0'")
	private String cgst_output_ledger;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String sgst_input_ledger;
	 
	@Column(columnDefinition="varchar(50) default '0'")
	private String sgst_output_ledger;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String igst_input_ledger;
	 
	@Column(columnDefinition="varchar(50) default '0'")
	private String igst_output_ledger;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "taxtype_Id")
    private Tax_type_master tax_type_master;
	
}
