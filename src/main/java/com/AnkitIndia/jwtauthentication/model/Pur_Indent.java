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
@Table(name="Pur_Indent")

public class Pur_Indent extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String indent_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String indent_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String indent_type;
	
	private String indent_date;
	
	//private Date required_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String valid_until;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String ser_item_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String referance_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String department;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String indent_by;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String fullfillment_type; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String fullfillment_by;
	
	/*@Column(columnDefinition="tinyint(1) default 0")
	private boolean unload_advice;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean weighment;*/
	
	@Column(columnDefinition="varchar(500) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String approved;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_req;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String close;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String c_reason;
	
	/*@Size(max = 50)
	private String doc_attachment;*/
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean enquiry_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean quotation_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean order_status;
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Indent",cascade = CascadeType.ALL)
	private Set<Pur_Indent_Details> pur_Indent_Details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Indent",cascade = CascadeType.ALL)
	private Set<Pur_Indent_Doc> pur_Indent_docs;
	
}
