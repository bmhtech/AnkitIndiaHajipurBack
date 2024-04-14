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
@Table(name="Pur_Quotation")
public class Pur_Quotation extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_no;
	
	private String quotation_date;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String required_date;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String valid_until;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String fullfillment_by;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String fullfillment_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String mode_of_quotation;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String quotation_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_service;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String item_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_refeance_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String department;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supplier_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String doc_no;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String doc_date;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String terms_condition;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_terms;
	
	private int delivery_perior;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transport_borne_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String mode_of_transport;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transport_name;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String payment_term;
		
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String loc_of_delivery;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String security_doc;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_req;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String confirmed_by;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String approved;

	@Column(columnDefinition="varchar(100) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean order_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String referance_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String passing_wt;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_Quotation",cascade = CascadeType.ALL)
	private Pur_quotation_Business_Partner_details pur_quotation_Business_Partner_details;

	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_QuotationSer",cascade = CascadeType.ALL)
	private Set<Pur_Quotation_Service> pur_Quotation_Service;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Quotation",cascade = CascadeType.ALL)
	private Set<Pur_Quotation_Doc> pur_Quotation_docs;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Quotation",cascade = CascadeType.ALL)
	private Set<Pur_Quotation_Broker> pur_Quotation_Broker;
	
}
